/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanOrganization;
import com.sg.superherosightings.model.SuperHumanSighting;
import java.time.LocalDateTime;
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
public class SuperHumanSightingDaoTest {

    SuperHumanSightingDao dao;

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHumanSightingDao", SuperHumanSightingDao.class);
    }

    /**
     * Test of create method, of class SuperHumanSightingDao.
     */
    @Test
    public void testCreate() {
        SuperHumanSighting x = create();

        assert x.getId() != null;
        assert "Test Loc".equals(x.getSighting().getLoc().getLocName());
        assert "TestName".equals(x.getSuperHuman().getName());

    }

    /**
     * Test of read method, of class SuperHumanSightingDao.
     */
    @Test
    public void testRead() {

        SuperHumanSighting x = create();

        assert x.getId() != null;
        assert "Test Loc".equals(x.getSighting().getLoc().getLocName());
        assert "TestName".equals(x.getSuperHuman().getName());

        SuperHumanSighting y = dao.read(x.getId());

        assert y.getId().equals(x.getId());
    }

    /**
     * Test of update method, of class SuperHumanSightingDao.
     */
    @Test
    public void testUpdate() {

        SuperHumanSighting x = create();

        assert x.getId() != null;
        assert "Test Loc".equals(x.getSighting().getLoc().getLocName());
        assert "TestName".equals(x.getSuperHuman().getName());

        Location loc = new Location();
        loc.setLocId(1L);
        loc.setLocName("Test Loc 2");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        Sighting sighting = new Sighting();
        sighting.setSightingId(1L);
        sighting.setDateTime(LocalDateTime.parse("2019-01-01T00:00:00"));
        sighting.setLoc(loc);

        x.setSighting(sighting);
        
        SuperHumanSighting y = dao.update(x);

        assert y.getId().equals(x.getId());
        assert x.getSighting().getLoc().getLocName().equals("Test Loc 2");
    }

    /**
     * Test of delete method, of class SuperHumanSightingDao.
     */
    @Test
    public void testDelete() {
        SuperHumanSighting x = create();
        
        dao.delete(x.getId());
        
        SuperHumanSighting y = dao.read(x.getId());
        
        assert y == null;
    }

    /**
     * Test of getSuperHumanSightingBySighting method, of class
     * SuperHumanSightingDao.
     */
    @Test
    public void testGetSuperHumanSightingBySighting() {
    }

    /**
     * Test of getSuperHumanSightingBySuperHuman method, of class
     * SuperHumanSightingDao.
     */
    @Test
    public void testGetSuperHumanSightingBySuperHuman() {
    }

    private SuperHumanSighting create() {

        SuperHuman superHuman = new SuperHuman();
        superHuman.setSuperId(1L);
        superHuman.setName("TestName");
        superHuman.setType("hero");
        superHuman.setDescription("TestDesc");
        superHuman.setSuperpower("TestPower");

        Location loc = new Location();
        loc.setLocId(1L);
        loc.setLocName("Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        Sighting sighting = new Sighting();
        sighting.setSightingId(1L);
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));
        sighting.setLoc(loc);

        SuperHumanSighting x = new SuperHumanSighting();
        x.setSuperHuman(superHuman);
        x.setSighting(sighting);

        return dao.create(x);
    }
}
