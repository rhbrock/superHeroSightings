/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class OrganizationServiceTest {

    private OrganizationService service;
//    private SuperHumanOrganizationService superOrgService;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("organizationService", OrganizationService.class);
        
//        superOrgService = ctx.getBean("superHumanOrgService", SuperHumanOrganizationService.class);
//
//        List<Organization> list = service.getListOfAllOrganizations();
//        for (Organization current : list) {
//            superOrgService.delete(current.getOrgId());
//            service.removeOrganization(current.getOrgId());
//        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addNewOrganization method, of class OrganizationService.
     */
    @Test
    public void testAddNewOrganization() throws Exception {

//        SuperHuman x = new SuperHuman();
//        x.setSuperId(1);
//        x.setName("Test");
//        x.setType("hero");
//        x.setSuperpower("Test");
//        x.setDescription("Test");
//
//        List<SuperHuman> list = new ArrayList<>();
//        list.add(x);

        try {
        Organization x = create();
        
        service.addNewOrganization(x);
        
        Organization fromDao = service.getOrgInfo(x.getOrgId());
        
        assert fromDao.getOrgId() == x.getOrgId();
        
        Organization orgFail = create();
        

            service.addNewOrganization(orgFail);
            fail("expected OrganizationExistsException");
        } catch (OrganizationExistsException e) {
            return;
        }
    }

    private Organization create() throws Exception{
        Organization o = new Organization();
        o.setName("orgTest");
        o.setDescription("test description");
        o.setStreetAddress("111");
        o.setZip(10012);
        o.setEmail("test@test.com");
        o.setPhoneNumber("111-111-1111");
        
        return o;
    }

}
