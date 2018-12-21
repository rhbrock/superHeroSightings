/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import com.sg.superherosightings.model.SuperHumanSighting;
import com.sg.superherosightings.service.LocationExistsException;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.OrganizationExistsException;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.SightingService;
import com.sg.superherosightings.service.SuperHumanExistsException;
import com.sg.superherosightings.service.SuperHumanOrganizationService;
import com.sg.superherosightings.service.SuperHumanService;
import com.sg.superherosightings.service.SuperHumanSightingService;
import com.sg.superherosightings.service.SuperHumanTypeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Roger Brock
 */
@Controller
@Component
public class SuperHeroSightingsController {

    LocationService locServ;
    OrganizationService orgServ;
    SightingService sightingServ;
    SuperHumanOrganizationService superOrgServ;
    SuperHumanService superServ;
    SuperHumanSightingService superSightingServ;

    @Inject
    public SuperHeroSightingsController(LocationService locServ,
            OrganizationService orgServ, SightingService sightingServ,
            SuperHumanOrganizationService superOrgServ,
            SuperHumanService superServ,
            SuperHumanSightingService superSightingServ) {
        this.locServ = locServ;
        this.orgServ = orgServ;
        this.sightingServ = sightingServ;
        this.superOrgServ = superOrgServ;
        this.superServ = superServ;
        this.superSightingServ = superSightingServ;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {

        model.addAttribute("sightingList", getTenSightingsMap());

        model.addAttribute("superList", getAllSupers());

        return "index";
    }

    @RequestMapping(value = "/supers", method = RequestMethod.GET)
    public String supersPage(Model model) {

        List<SuperHuman> heroList = superServ.getHeros();
        List<SuperHuman> villainList = superServ.getVillains();

        SuperHuman superHuman = new SuperHuman();
        model.addAttribute("superHuman", superHuman);

        model.addAttribute("heroList", heroList);
        model.addAttribute("villainList", villainList);
        model.addAttribute("superList", getAllSupers());

        return "supers";
    }

    @RequestMapping(value = "/super", method = RequestMethod.GET)
    public String superInfoPage(HttpServletRequest request, Model model) {

        String id = request.getParameter("superId");
        long longId = Long.parseLong(id);

        SuperHuman superHuman = superServ.getSuperHumanInfo(longId);
        List<Organization> orgList = orgServ.getSuperOrgBySuperHuman(superHuman);

        model.addAttribute("superHuman", superHuman);
        model.addAttribute("orgList", orgList);
        model.addAttribute("superList", getAllSupers());

        return "super";
    }

    @RequestMapping(value = "/newSuper", method = RequestMethod.POST)
    public String newSuper(@Valid @ModelAttribute("superHuman") SuperHuman superHuman, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "supers";
        }

//        SuperHuman sh = new SuperHuman();
//
//        sh.setName(request.getParameter("name"));
//        sh.setType(request.getParameter("type").toLowerCase());
//        sh.setSuperpower(request.getParameter("superpower"));
//        sh.setDescription(request.getParameter("description"));
        try {

            superServ.createNewSuperHuman(superHuman);

        } catch (SuperHumanExistsException | SuperHumanTypeException ex) {

            model.addAttribute("error", ex);

            return "redirect:supers";
        }

        return "redirect:supers";
    }

    @RequestMapping(value = "/editSuper", method = RequestMethod.POST)
    public String editSuper(@Valid @ModelAttribute("superHuman") SuperHuman superHuman, BindingResult result,
            Model model, HttpServletRequest request) {

        String superId = request.getParameter("superId");

        if (result.hasErrors()) {

            return "super";
        }

        try {

            superServ.editExisitingSuperHuman(superHuman);

        } catch (SuperHumanTypeException ex) {

            model.addAttribute(ex);

            return "redirect:super?superId=" + superId;
        }

        return "redirect:super?superId=" + superId;
    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(HttpServletRequest request, Model model) {

        String id = request.getParameter("superId");
        long longId = Long.parseLong(id);

        //superOrgServ.delete(longId);
        superServ.removeSuperHuman(longId);

        return "redirect:supers";
    }

    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public String orgsPage(Model model) {

        List<Organization> orgList = orgServ.getListOfAllOrganizations();

        Organization org = new Organization();
        model.addAttribute("org", org);

        model.addAttribute("orgs", orgList);
        model.addAttribute("superList", getAllSupers());

        return "orgs";
    }

    @RequestMapping(value = "/org", method = RequestMethod.GET)
    public String orgInfoPage(HttpServletRequest request, Model model) {

        String id = request.getParameter("orgId");

        long longId = Long.parseLong(id);

        Organization org = orgServ.getOrgInfo(longId);

        model.addAttribute("org", org);

        List<SuperHumanOrganization> superHumanOrgList
                = superOrgServ.getSuperHumanOrganizationByOrganization(org);

        model.addAttribute("superOrgList", superHumanOrgList);

        model.addAttribute("superList", getAllSupers());

        return "org";
    }

    @RequestMapping(value = "/addAffiliate", method = RequestMethod.POST)
    public String addAffiliate(HttpServletRequest request, Model model) {

        String id = request.getParameter("super");
        long superLongId = Long.parseLong(id);

        String orgId = request.getParameter("orgId");
        long orgLongId = Long.parseLong(orgId);

        SuperHuman superHuman = superServ.getSuperHumanInfo(superLongId);
        Organization org = orgServ.getOrgInfo(orgLongId);

        SuperHumanOrganization sho = new SuperHumanOrganization();
        sho.setSuperHuman(superHuman);
        sho.setOrganization(org);

        superOrgServ.create(sho);

        return "redirect:org?orgId=" + orgId;
    }

    @RequestMapping(value = "/removeAffiliate", method = RequestMethod.GET)
    public String removeAffiliate(HttpServletRequest request, Model model) {

        String id = request.getParameter("superId");
        long longId = Long.parseLong(id);

        superOrgServ.delete(longId);

        String orgId = request.getParameter("orgId");

        return "redirect:org?orgId=" + orgId;
    }

    @RequestMapping(value = "/newOrg", method = RequestMethod.POST)
    public String newOrg(@Valid @ModelAttribute("org") Organization org, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "orgs";
        }

        try {

            orgServ.addNewOrganization(org);

        } catch (OrganizationExistsException ex) {

            model.addAttribute(ex);

            return "redirect:orgs";
        }
        return "redirect:orgs";
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public String editOrg(@Valid @ModelAttribute("org") Organization org, BindingResult result,
            HttpServletRequest request, Model model) {

        String orgId = request.getParameter("orgId");

        if (result.hasErrors()) {
            return "org";
        }

        orgServ.editOrganizationInfo(org);

        return "redirect:org?orgId=" + orgId;
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.GET)
    public String deleteOrg(HttpServletRequest request, Model model) {

        String id = request.getParameter("orgId");
        long longId = Long.parseLong(id);

        orgServ.removeOrganization(longId);

        return "redirect:orgs";
    }

    @RequestMapping(value = "/locs", method = RequestMethod.GET)
    public String locsPage(Model model) {

        List<Location> locList = locServ.getAllLocations();

        Location loc = new Location();
        model.addAttribute("loc", loc);

        model.addAttribute("locList", locList);
        model.addAttribute("superList", getAllSupers());

        return "locs";
    }

    @RequestMapping(value = "/loc", method = RequestMethod.GET)
    public String locInfoPage(HttpServletRequest request, Model model) {

        String id = request.getParameter("locId");

        long longId = Integer.parseInt(id);

        Location loc = locServ.getLocationInfo(longId);

        model.addAttribute("loc", loc);
        model.addAttribute("superList", getAllSupers());

        return "loc";
    }

    @RequestMapping(value = "/newLoc", method = RequestMethod.POST)
    public String newLoc(@Valid @ModelAttribute("loc") Location loc, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "locs";
        }

        try {

            locServ.addNewLocation(loc);

        } catch (LocationExistsException ex) {

            model.addAttribute("error", ex.getMessage());

            return "re:direct:locs";
        }
        return "redirect:locs";
    }

    @RequestMapping(value = "/editLoc", method = RequestMethod.POST)
    public String editLoc(@Valid @ModelAttribute("loc") Location loc, BindingResult result,
            HttpServletRequest request, Model model) {

        String locId = request.getParameter("locId");

        if (result.hasErrors()) {
            return "loc";
        }

        try {
            locServ.editLocationInfo(loc);
        } catch (LocationExistsException ex) {
            return "redirect:loc?locId=" + locId;
        }
        return "redirect:loc?locId=" + locId;
    }

    @RequestMapping(value = "/deleteLoc", method = RequestMethod.GET)
    public String deleteLoc(HttpServletRequest request, Model model) {

        String id = request.getParameter("locId");
        long longId = Long.parseLong(id);

        locServ.deleteLocation(longId);

        return "redirect:locs";
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    public String sightingsPage(SuperHumanSighting superHumanSighting, HttpServletRequest request, Model model) {

        model.addAttribute("sightings", getAllSightingsMap());
        model.addAttribute("superList", getAllSupers());

        return "sightings";
    }

    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String sightingPage(HttpServletRequest request, Model model) {

        String id = request.getParameter("sightingId");

        long longId = Long.parseLong(id);

        Sighting sighting = sightingServ.getSightingInfo(longId);
        List<SuperHumanSighting> shs = superSightingServ.getSuperHumanSightingBySighting(sighting);
        List<SuperHuman> superHumanList = superServ.getSuperHumansBySighting(sighting);

        Map<Sighting, List<SuperHuman>> sightingMap = new HashMap<>();
        sightingMap.put(sighting, superHumanList);

        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingMap", sightingMap);
        model.addAttribute("superHumanList", superHumanList);
        model.addAttribute("superList", getAllSupers());

        return "sighting";
    }

    @RequestMapping(value = "/newSighting", method = RequestMethod.POST)
    public String newSighting(HttpServletRequest request, Model model) {

        String superId = request.getParameter("super");
        Long intId = Long.parseLong(superId);
        SuperHuman superHuman = superServ.getSuperHumanInfo(intId);

        Location loc = new Location();
        loc.setLocName(request.getParameter("locName"));
        loc.setLocDesc(request.getParameter("locDesc"));
        loc.setStreetAddress(request.getParameter("streetAddress"));
        String zip = request.getParameter("zip");
        loc.setZip(Integer.parseInt(zip));
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");
        loc.setLat(Double.parseDouble(lat));
        loc.setLon(Double.parseDouble(lon));

        try {
            locServ.addNewLocation(loc);
        } catch (LocationExistsException ex) {
            model.addAttribute(ex);
        }

        Sighting sighting = new Sighting();

        String dateTime = request.getParameter("dateTime");

        if (!dateTime.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
            sighting.setDateTime(localDateTime);
        }

        sighting.setLoc(loc);

        sightingServ.newSighting(sighting);

        SuperHumanSighting superHumanSighting = new SuperHumanSighting();
        superHumanSighting.setSuperHuman(superHuman);
        superHumanSighting.setSighting(sighting);

        superSightingServ.create(superHumanSighting);

        return "redirect:/";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, 
            HttpServletRequest request, Model model) {

        String sightingId = request.getParameter("sightingId");
        long longSightingId = Long.parseLong(sightingId);

        if (result.hasErrors()) {
            return "sighting";
        }

        String[] addedSupers = request.getParameterValues("addSupers");
        String[] deleteSupers = request.getParameterValues("deleteSupers");

        List<SuperHumanSighting> superHumanSightingList
                = superSightingServ.getSuperHumanSightingBySighting(sighting);

        List<Long> deletedId = new ArrayList<>();

        if (deleteSupers != null) {

            for (String x : deleteSupers) {
                deletedId.add(Long.parseLong(x));
            }

            for (Long id : deletedId) {
                SuperHuman superHuman = superServ.getSuperHumanInfo(id);
                for (SuperHumanSighting current : superHumanSightingList) {
                    if (id == current.getSuperHuman().getSuperId()) {
                        superSightingServ.delete(current.getId());
                    }
                }
            }
        }

        List<Long> addedId = new ArrayList<>();

        if (addedSupers != null) {

            for (String x : addedSupers) {
                addedId.add(Long.parseLong(x));
            }

            for (Long id : addedId) {
                SuperHuman superHuman = superServ.getSuperHumanInfo(id);
                SuperHumanSighting shs = new SuperHumanSighting();
                shs.setSighting(sighting);
                shs.setSuperHuman(superHuman);
                superSightingServ.create(shs);
            }
        }

        try {
            locServ.editLocationInfo(sighting.getLoc());
        } catch (LocationExistsException ex) {
            return "redirect:sighting?sightingId=" + sightingId;
        }
        sightingServ.updateSightingInfo(sighting);

        return "redirect:sighting?sightingId=" + sightingId;
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model
    ) {

        String id = request.getParameter("sightingId");
        long longId = Long.parseLong(id);

        sightingServ.deleteSighting(longId);

        return "redirect:sightings?sightingId=" + id;
    }

    //Helpers back to various services 
    private Map<Sighting, List<SuperHuman>> getTenSightingsMap() {

        List<Sighting> sightings = sightingServ.getMostRecentNumberOfSightings(10);

        Map<Sighting, List<SuperHuman>> sightingMap = new LinkedHashMap<>();

        for (Sighting current : sightings) {

            sightingMap.put(current, superServ.getSuperHumansBySighting(current));

        }

        return sightingMap;
    }

    private Map<Sighting, List<SuperHuman>> getAllSightingsMap() {

        List<Sighting> sightings = sightingServ.getAllSightings();

        Map<Sighting, List<SuperHuman>> sightingMap = new LinkedHashMap<>();

        for (Sighting current : sightings) {

            sightingMap.put(current, superServ.getSuperHumansBySighting(current));

        }

        return sightingMap;
    }

    private List<SuperHuman> getAllSupers() {

        List<SuperHuman> superList = superServ.getAllSuperHumans();

        return superList;

    }
}
