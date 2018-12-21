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
public interface SightingDao {

    Sighting addSighting(Sighting sighting);

    void deleteSighting(Long sightingId);

    Sighting editSighting(Sighting sighting);

    List<Sighting> getAllSightings();

    Sighting getSighting(Long sightingId);

    List<Sighting> getSightingsBySuperHuman(SuperHuman superHuman);
    
    List<Sighting> getSightingsAtLocation(Location location);

}
