/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface SuperHumanService {

    SuperHuman createNewSuperHuman(SuperHuman superHuman)
            throws SuperHumanExistsException, SuperHumanTypeException;

    void removeSuperHuman(Long superId);

    SuperHuman editExisitingSuperHuman(SuperHuman superHuman)
            throws SuperHumanTypeException;

    SuperHuman getSuperHumanInfo(Long superId);

    List<SuperHuman> getAllSuperHumans();

    List<SuperHuman> getSuperHumansBySighting(Sighting sighting);

    List<SuperHuman> getSuperHumansByOrganization(Organization org);
    
    List<SuperHuman> getHeros();
    
    List<SuperHuman> getVillains();

}
