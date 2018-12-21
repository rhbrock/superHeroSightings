/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.dao.SuperHumanDao;
import com.sg.superherosightings.dao.SuperHumanOrganizationDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanOrganizationServiceImpl implements SuperHumanOrganizationService {

    SuperHumanOrganizationDao dao;
    SuperHumanDao superHumanDao;
    OrganizationDao orgDao;
    SuperHeroAuditDao auditDao;

    @Inject
    public SuperHumanOrganizationServiceImpl(SuperHumanOrganizationDao dao,
            SuperHumanDao superHumanDao, OrganizationDao orgDao, SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.superHumanDao = superHumanDao;
        this.orgDao = orgDao;
        this.auditDao = auditDao;
    }

    @Override
    public SuperHumanOrganization create(SuperHumanOrganization superHumanOrg) {
        return dao.create(superHumanOrg);
    }

    @Override
    public SuperHumanOrganization read(Long id) {

        SuperHumanOrganization superHumanOrg = dao.read(id);

        SuperHuman superHuman = superHumanDao.read(superHumanOrg.getSuperHuman().getSuperId());
        superHumanOrg.setSuperHuman(superHuman);

        Organization org = orgDao.getSuperOrg(superHumanOrg.getId());
        superHumanOrg.setOrganization(org);

        return superHumanOrg;
    }

    @Override
    public SuperHumanOrganization update(SuperHumanOrganization superHumanOrg) {

        return dao.update(superHumanOrg);
    }

    @Override
    public void delete(Long id) {
//        SuperHumanOrganization superHumanOrg = dao.read(id);
//
//        superHumanDao.delete(superHumanOrg.getSuperHuman().getSuperId());
//
//        orgDao.deleteSuperOrg(superHumanOrg.getOrganization().getOrgId());

        dao.delete(id);
    }

    @Override
    public List<SuperHumanOrganization> getSuperHumanOrganizationBySuperHuman(SuperHuman superHuman) {

        List<SuperHumanOrganization> list = dao.getSuperHumanOrganizationBySuperHuman(superHuman);

        for (SuperHumanOrganization current : list) {
            current.setSuperHuman(superHumanDao.read(current.getSuperHuman().getSuperId()));
            current.setOrganization(orgDao.getSuperOrg(current.getOrganization().getOrgId()));
        }

        return list;
    }

    @Override
    public List<SuperHumanOrganization> getSuperHumanOrganizationByOrganization(Organization org) {
        List<SuperHumanOrganization> list = dao.getSuperHumanOrganizationByOrganization(org);

        for (SuperHumanOrganization current : list) {
            current.setSuperHuman(superHumanDao.read(current.getSuperHuman().getSuperId()));
            current.setOrganization(orgDao.getSuperOrg(current.getOrganization().getOrgId()));
        }

        return list;
    }

}
