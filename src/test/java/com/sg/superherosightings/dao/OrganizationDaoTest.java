/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class OrganizationDaoTest {

    private OrganizationDao orgDao;

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        orgDao = ctx.getBean("orgDao", OrganizationDao.class);

    }

    /**
     * Test of addSuperOrg method, of class OrganizationDao.
     */
    @Test
    public void testAddSuperOrg() {

        Organization org = createOrg();

        assert org.getOrgId() != null;
        assert org.getPhoneNumber().equals("111-111-1111");

    }

    /**
     * Test of deleteSuperOrg method, of class OrganizationDao.
     */
    @Test
    public void testDeleteSuperOrg() {

        Organization org = createOrg();

        assert org.getOrgId() != null;
        assert org.getPhoneNumber().equals("111-111-1111");

        orgDao.deleteSuperOrg(org.getOrgId());

        Organization fromDao = orgDao.getSuperOrg(org.getOrgId());
        
        assert fromDao == null;

    }

    /**
     * Test of editSuperOrg method, of class OrganizationDao.
     */
    @Test
    public void testEditSuperOrg() {

        Organization org = createOrg();

        assert org.getOrgId() != null;
        assert org.getName().equals("orgTest");
        assert org.getPhoneNumber().equals("111-111-1111");

        org.setName("orgTest2");

        orgDao.editSuperOrg(org);

        Organization fromDao = orgDao.getSuperOrg(org.getOrgId());

        assert fromDao.getName().equals("orgTest2");

    }

    /**
     * Test of getAllSuperOrgsAsList method, of class OrganizationDao.
     */
    @Test
    public void testGetAllSuperOrgsAsList() {

        Organization org = createOrg();

        List<Organization> superList = orgDao.getAllSuperOrgsAsList();
        int size = superList.size();

        assert superList != null;
        assert size > 0;

    }

    /**
     * Test of getSuperOrg method, of class OrganizationDao.
     */
    @Test
    public void testGetSuperOrg() {

        //tested above 
    }

    @Test
    public void getSuperOrgBySuperHuman() {

        Organization org = createOrg();

    }

    private Organization createOrg() {

        Organization org = new Organization();
        org.setName("orgTest");
        org.setDescription("test description");
        org.setStreetAddress("111");
        org.setZip(10012);
        org.setEmail("test@test.com");
        org.setPhoneNumber("111-111-1111");

        return orgDao.addSuperOrg(org);
    }
}
