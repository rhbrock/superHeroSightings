/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface LocationService {
    
    Location addNewLocation(Location location)
            throws LocationExistsException;
    
    void deleteLocation(Long locId);
    
    Location editLocationInfo(Location location)
            throws LocationExistsException;
    
    Location getLocationInfo(Long locId);
    
    List<Location> getAllLocations();
    
    List<Location> getLocationListBySuperHuman(SuperHuman superHuman);
    
}
