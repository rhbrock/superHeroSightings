/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface SightingService {

    Sighting newSighting(Sighting sighting);

    void deleteSighting(Long sightingId);

    Sighting updateSightingInfo(Sighting sighting);

    Sighting getSightingInfo(Long sightingId);

    List<Sighting> getAllSightings();
    
    List<Sighting> getMostRecentNumberOfSightings(int numberOf);

    List<Sighting> getSightingsBySuperHuman(SuperHuman superHuman);
    
    List<Sighting> getSightingsAtLocation(Location location);

}
