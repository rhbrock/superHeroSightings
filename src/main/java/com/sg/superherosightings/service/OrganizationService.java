/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface OrganizationService {

    Organization addNewOrganization(Organization org)
            throws OrganizationExistsException;

    void removeOrganization(Long orgId);

    Organization editOrganizationInfo(Organization org);

    Organization getOrgInfo(Long orgId);

    List<Organization> getListOfAllOrganizations();

    List<Organization> getSuperOrgBySuperHuman(SuperHuman superHuman);

}
