/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class SightingServiceImpl implements SightingService {

    SightingDao dao;
    LocationDao locDao;
    private SuperHeroAuditDao auditDao;

    @Inject
    public SightingServiceImpl(SightingDao dao, LocationDao locDao, SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.locDao = locDao;
        this.auditDao = auditDao;
    }

    @Override
    public Sighting newSighting(Sighting sighting) {

        if (sighting.getDateTime() == null) {
            //set time stamp to sighting object
            LocalDateTime dateTime = LocalDateTime.now();
            sighting.setDateTime(dateTime);
        }

        return dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(Long sightingId) {
        dao.deleteSighting(sightingId);
    }

    @Override
    public Sighting updateSightingInfo(Sighting sighting) {
        return dao.editSighting(sighting);
    }

    @Override
    public Sighting getSightingInfo(Long sightingId) {
        Sighting sighting = dao.getSighting(sightingId);

        Location loc = locDao.read(sighting.getLoc().getLocId());

        sighting.setLoc(loc);

        return sighting;
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> list = dao.getAllSightings();

        for (Sighting current : list) {
            Location loc = locDao.read(current.getLoc().getLocId());
            current.setLoc(loc);

        }

        return list;
    }

    @Override
    public List<Sighting> getSightingsBySuperHuman(SuperHuman superHuman) {

        List<Sighting> list = dao.getSightingsBySuperHuman(superHuman);

        for (Sighting current : list) {
            Location loc = locDao.read(current.getLoc().getLocId());
            current.setLoc(loc);
        }

        return list;
    }

    @Override
    public List<Sighting> getSightingsAtLocation(Location location) {

        List<Sighting> list = dao.getSightingsAtLocation(location);

        for (Sighting current : list) {
            Location loc = locDao.read(current.getLoc().getLocId());
            current.setLoc(loc);
        }

        return list;
    }

    @Override
    public List<Sighting> getMostRecentNumberOfSightings(int numberOf) {

        List<Sighting> sightingList = getAllSightings();

        int listSize = sightingList.size();

        //lowest ID to be greater than for returned list
        int x = listSize - numberOf;

        List<Sighting> shortList = sightingList.stream()
                .filter(s -> s.getSightingId() > x)
                .collect(Collectors.toList());

        Collections.reverse(shortList);

        return shortList;
    }

}
