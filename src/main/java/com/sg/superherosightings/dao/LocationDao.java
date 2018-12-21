/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface LocationDao {

    Location create(Location loc);

    Location read(Long locId);

    Location update(Location loc);

    void delete(Long locId);

    List<Location> getAllLoc();  
    
    List<Location> getLocationListBySuperHuman(SuperHuman superHuman);
    

}
