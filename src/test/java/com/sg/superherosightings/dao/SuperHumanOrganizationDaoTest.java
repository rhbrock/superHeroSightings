/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanOrganizationDaoTest {

    SuperHumanOrganizationDao dao;

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHumanOrgDao", SuperHumanOrganizationDao.class);


    }

    /**
     * Test of create method, of class SuperHumanOrganizationDao.
     */
    @Test
    public void testCreate() {

        SuperHumanOrganization x = create();

        assert x.getId() != null;
        assert "orgTest".equals(x.getOrganization().getName());
        assert "TestName".equals(x.getSuperHuman().getName());
    }

    /**
     * Test of read method, of class SuperHumanOrganizationDao.
     */
    @Test
    public void testRead() {
        SuperHumanOrganization x = create();

        assert x.getId() != null;
        assert "orgTest".equals(x.getOrganization().getName());
        assert "TestName".equals(x.getSuperHuman().getName());

        SuperHumanOrganization fromDao = dao.read(x.getId());

        //assert fromDao.getId() == x.getId();
    }

    /**
     * Test of update method, of class SuperHumanOrganizationDao.
     */
    @Test
    public void testUpdate() {
        SuperHumanOrganization x = create();

        assert x.getId() != null;
        assert "orgTest".equals(x.getOrganization().getName());
        assert "TestName".equals(x.getSuperHuman().getName());

        Organization org = new Organization();
        org.setOrgId(2L);
        org.setName("orgTest2");
        org.setDescription("test description");
        org.setStreetAddress("111");
        org.setZip(10011);
        org.setEmail("test2@test2.com");
        org.setPhoneNumber("111-111-1111");

        x.setOrganization(org);

        SuperHumanOrganization fromDao = dao.update(x);

        assert fromDao.getOrganization().getName().equals("orgTest2");
    }

    /**
     * Test of delete method, of class SuperHumanOrganizationDao.
     */
    @Test
    public void testDelete() {
        SuperHumanOrganization x = create();

        assert x != null;

        dao.delete(x.getId());

        SuperHumanOrganization y = dao.read(x.getId());

        assert y == null;
    }

    /**
     * Test of getSuperHumanOrganizationBySuperHuman method, of class
     * SuperHumanOrganizationDao.
     */
    @Test
    public void testGetSuperHumanOrganizationBySuperHuman() {

        SuperHumanOrganization x = create();

        List<SuperHumanOrganization> list
                = dao.getSuperHumanOrganizationBySuperHuman(x.getSuperHuman());

        assert list != null;
    }

    /**
     * Test of getSuperHumanOrganizationByOrganization method, of class
     * SuperHumanOrganizationDao.
     */
    @Test
    public void testGetSuperHumanOrganizationByOrganization() {
    }

    private SuperHumanOrganization create() {

        Organization org = new Organization();
        org.setOrgId(1L);
        org.setName("orgTest");
        org.setDescription("test description");
        org.setStreetAddress("111");
        org.setZip(10012);
        org.setEmail("test@test.com");
        org.setPhoneNumber("111-111-1111");

        SuperHuman superHuman = new SuperHuman();
        superHuman.setSuperId(1L);
        superHuman.setName("TestName");
        superHuman.setType("hero");
        superHuman.setDescription("TestDesc");
        superHuman.setSuperpower("TestPower");

        SuperHumanOrganization x = new SuperHumanOrganization();
        x.setOrganization(org);
        x.setSuperHuman(superHuman);

        return dao.create(x);
    }

}
