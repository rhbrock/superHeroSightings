/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface SuperHumanDao {

    SuperHuman create(SuperHuman superHuman);

    SuperHuman read(Long superId);

    SuperHuman update(SuperHuman superHuman);

    void delete(Long superId);

    List<SuperHuman> getAll();

    List<SuperHuman> getSuperHumansBySighting(Sighting sighting);

    List<SuperHuman> getSuperHumansByOrganization(Organization org);
}
