/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperHumanDao;
import com.sg.superherosightings.model.SuperHuman;
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
public class SuperHumanServiceTest {

    private SuperHumanService service;

    public SuperHumanServiceTest() {
    }

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

        service = ctx.getBean("superHumanService", SuperHumanService.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createNewSuperHuman method for equal name, of class
     * SuperHumanService.
     */
    @Test
    public void testCreateNewSuperHumanNameThatExists() throws Exception {

        try {
            SuperHuman s = new SuperHuman();
            s.setName("Name");
            s.setType("hero");
            s.setSuperpower("superpower");
            s.setDescription("test description");

            service.createNewSuperHuman(s);
            fail("Expected SuperHumanExistsException");
        } catch (SuperHumanExistsException e) {
            return;
        }
    }

    /**
     * Test of createNewSuperHuman method for non-type, of class
     * SuperHumanService.
     */
    @Test
    public void testCreateNewSuperHumanNoType() throws Exception {
        
        try {
            SuperHuman s = new SuperHuman();
            //purposeful wrong name for testing type
            s.setName("AnotherName");
            //type blank.  Should throw exception and pass test.
            s.setType("");
            s.setSuperpower("superpower");
            s.setDescription("test description");

            service.createNewSuperHuman(s);
            fail("Expected SuperHumanTypeException");
        } catch (SuperHumanTypeException e) {
            return;
        }

    }

    /**
     * Test of createNewSuperHuman method for non-type, of class
     * SuperHumanService.
     */
    @Test
    public void testCreateNewSuperHumanWrongType() throws Exception {

        SuperHuman s = new SuperHuman();
        //purposeful wrong name for testing type
        s.setName("AnotherName");
        //type wrong.  Should throw exception and pass test
        s.setType("dog");
        s.setSuperpower("superpower");
        s.setDescription("test description");

        try {
            service.createNewSuperHuman(s);
            fail("Expected SuperHumanTypeException");
        } catch (SuperHumanTypeException e) {
            return;
        }

    }

    /**
     * Test of editExisitingSuperHuman no type method, of class
     * SuperHumanService.
     */
    @Test
    public void testEditExisitingSuperHumanNoType() throws Exception {

        SuperHuman s = service.getSuperHumanInfo(1L);
        s.setType("");

        try {
            service.editExisitingSuperHuman(s);
            fail("Expected SuperHumanTypeException");
        } catch (SuperHumanTypeException e) {
            return;
        }
    }

    /**
     * Test of editExisitingSuperHuman wrong type method, of class
     * SuperHumanService.
     */
    @Test
    public void testEditExisitingSuperHumanWrongType() throws Exception {

        SuperHuman s = service.getSuperHumanInfo(1L);
        s.setType("dog");

        try {
            service.editExisitingSuperHuman(s);
            fail("Expected SuperHumanTypeException");
        } catch (SuperHumanTypeException e) {
            return;
        }
    }

}
