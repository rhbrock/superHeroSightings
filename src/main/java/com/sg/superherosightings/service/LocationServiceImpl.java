/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class LocationServiceImpl implements LocationService {

    LocationDao dao;
    private SuperHeroAuditDao auditDao;

    @Inject
    public LocationServiceImpl(LocationDao dao, SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Location addNewLocation(Location location)
            throws LocationExistsException {

        List<Location> locList = dao.getAllLoc();

        for (Location x : locList) {
            if (location.getLocName().equalsIgnoreCase(x.getLocName())) {
                throw new LocationExistsException("Location you are trying"
                        + " to add already exists.  "
                        + "Please select location from provided list.");
            }
        }
        
        return dao.create(location);
    }

    @Override
    public void deleteLocation(Long locId) {
        dao.delete(locId);
    }

    @Override
    public Location editLocationInfo(Location location){
        return dao.update(location);
    }

    @Override
    public Location getLocationInfo(Long locId) {
        return dao.read(locId);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLoc(); 
    }

    @Override
    public List<Location> getLocationListBySuperHuman(SuperHuman superHuman) {
        return dao.getLocationListBySuperHuman(superHuman);
    }

}
