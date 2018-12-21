/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanSighting;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface SuperHumanSightingService {

    public SuperHumanSighting create(SuperHumanSighting superHumanSighting);

    public SuperHumanSighting read(Long id);

    public SuperHumanSighting update(SuperHumanSighting superHumanSighting);

    public void delete(Long id);

    public List<SuperHumanSighting> getSuperHumanSightingBySighting(Sighting sighting);

    public List<SuperHumanSighting> getSuperHumanSightingBySuperHuman(SuperHuman superHuman);
    
//    public List<SuperHumanSighting> getSuperHumanSightingsForDisplay(List<Sighting> sightings);

    
}
