/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class SightingServiceTest {

    private SightingService service;
    private LocationService locService;
//    private SuperHumanSightingService superHumanSightingService;

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("sightingService", SightingService.class);
        locService = ctx.getBean("locationService", LocationService.class);

//        superHumanSightingService = ctx.getBean("superHumanSightingService", SuperHumanSightingService.class);
//
//        List<Sighting> sightingList = service.getAllSightings();
//        for (Sighting currentSighting : sightingList) {
//            superHumanSightingService.delete(currentSighting.getSightingId());
//            service.deleteSighting(currentSighting.getLoc().getLocId());
//        }
//
//        List<Location> list = locService.getAllLocations();
//        for (Location current : list) {
//            locService.deleteLocation(current.getLocId());
//        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of newSighting method, of class SightingService.
     */
    @Test
    public void testNewSighting() throws Exception {

        Sighting s = new Sighting();

        assert s.getDateTime() == null;

        Sighting sighting = service.newSighting(s);

        //LocalDateTime dateTime = sighting.getDateTime();
        assert sighting.getDateTime() != null;

    }

//    @Test
//    public void create() throws Exception {
//               
//        Location loc = new Location();
//        loc.setLocName("Sighting Test Loc");
//        loc.setLocDesc("Test Loc Desc");
//        loc.setStreetAddress("2600 Malcom Bridge Rd");
//        loc.setZip(10012);
//        loc.setLat(33.914683);
//        loc.setLon(-83.505634);
//        locService.addNewLocation(loc);
//        
//        Sighting sighting = new Sighting();
//        sighting.setLoc(loc);
//        service.newSighting(sighting);
//
//
//        Sighting fromDao = service.getSightingInfo(sighting.getSightingId());
//
//        assert "Sighting Test Loc".equals(fromDao.getLoc().getLocName());
//
//    }
//
//    @Test
//    public void delete() throws Exception {
//
//        Location loc = new Location();
//        loc.setLocName("Sighting Test2 Loc");
//        loc.setLocDesc("Test Loc Desc");
//        loc.setStreetAddress("2600 Malcom Bridge Rd");
//        loc.setZip(10012);
//        loc.setLat(33.914683);
//        loc.setLon(-83.505634);
//
//        locService.addNewLocation(loc);
//
//        Sighting sighting = new Sighting();
//        sighting.setLoc(loc);
//
//        service.newSighting(sighting);
//
//        Sighting fromDao = service.getSightingInfo(sighting.getSightingId());
//
//        assert "Sighting Test2 Loc".equals(fromDao.getLoc().getLocName());
//
//        service.deleteSighting(sighting.getSightingId());
//
//        fromDao = service.getSightingInfo(sighting.getSightingId());
//
//        assert fromDao == null;
//    }
}
