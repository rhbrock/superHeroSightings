/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface SuperHumanOrganizationService {

    public SuperHumanOrganization create(SuperHumanOrganization superHumanOrg);

    public SuperHumanOrganization read(Long id);

    public SuperHumanOrganization update(SuperHumanOrganization superHumanOrg);

    public void delete(Long id);

    public List<SuperHumanOrganization> getSuperHumanOrganizationBySuperHuman(SuperHuman superHuman);

    public List<SuperHumanOrganization> getSuperHumanOrganizationByOrganization(Organization org);

}
