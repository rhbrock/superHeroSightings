/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface OrganizationDao {

    Organization addSuperOrg(Organization org);

    void deleteSuperOrg(Long id);

    Organization editSuperOrg(Organization org);

    List<Organization> getAllSuperOrgsAsList();

    Organization getSuperOrg(Long id);

    List<Organization> getSuperOrgBySuperHuman(SuperHuman superHuman);
}
