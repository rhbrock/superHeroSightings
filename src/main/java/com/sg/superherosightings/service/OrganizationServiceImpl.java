/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao dao;
    SuperHeroAuditDao auditDao;
    
    @Inject
    public OrganizationServiceImpl(OrganizationDao dao, SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Organization addNewOrganization(Organization org)
            throws OrganizationExistsException {
        
        List<Organization> orgList = dao.getAllSuperOrgsAsList();
        
        for(Organization o : orgList){
            if(o.getName().equalsIgnoreCase(org.getName())){
                throw new OrganizationExistsException("Organization you are "
                        + "trying to add already exists. "
                        + "Please select organization from list.");
            }
        }
        return dao.addSuperOrg(org);
    }

    @Override
    public void removeOrganization(Long orgId) {
        dao.deleteSuperOrg(orgId);
    }

    @Override
    public Organization editOrganizationInfo(Organization org){
        return dao.editSuperOrg(org);
    }

    @Override
    public Organization getOrgInfo(Long orgId) {
        return dao.getSuperOrg(orgId);
    }

    @Override
    public List<Organization> getListOfAllOrganizations() {
        return dao.getAllSuperOrgsAsList();
    }

    @Override
    public List<Organization> getSuperOrgBySuperHuman(SuperHuman superHuman) {
        return dao.getSuperOrgBySuperHuman(superHuman);
    }

}
