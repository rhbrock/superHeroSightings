/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanOrganizationServiceTest {

    SuperHumanOrganizationService service;

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("superHumanOrgService", SuperHumanOrganizationService.class);

    }

    /**
     * Test of create method, of class SuperHumanOrganizationService.
     */
    @Test
    public void testCreate() {

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
        
        service.create(x);
        
        SuperHumanOrganization fromDao = service.read(x.getId());
        
        assert "orgTest".equals(x.getOrganization().getName());

    }

    /**
     * Test of read method, of class SuperHumanOrganizationService.
     */
    @Test
    public void testRead() {
    }

    /**
     * Test of update method, of class SuperHumanOrganizationService.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class SuperHumanOrganizationService.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of getSuperHumanOrganizationBySuperHuman method, of class
     * SuperHumanOrganizationService.
     */
    @Test
    public void testGetSuperHumanOrganizationBySuperHuman() {
    }

    /**
     * Test of getSuperHumanOrganizationByOrganization method, of class
     * SuperHumanOrganizationService.
     */
    @Test
    public void testGetSuperHumanOrganizationByOrganization() {
    }

}
