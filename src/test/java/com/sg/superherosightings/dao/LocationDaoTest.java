/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanSighting;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class LocationDaoTest {

    
    private LocationDao dao;
    private SightingDao sightingDao;
    private SuperHumanSightingDao superHumanSightingDao;
    private SuperHumanDao superHumanDao;

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("locationDao", LocationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superHumanSightingDao = ctx.getBean("superHumanSightingDao", SuperHumanSightingDao.class);
        superHumanDao = ctx.getBean("superHumanDao", SuperHumanDao.class);
    }

    /**
     * Test of addLoc method, of class LocationDao.
     */
    @Test
    public void testAddLoc() {

        Location loc = createLoc();

        assert loc.getLocId() != null;
        assert loc.getLocName().equals("Test Loc");
    }

    /**
     * Test of deleteLoc method, of class LocationDao.
     */
    @Test
    public void testDeleteLoc() {

        Location loc = createLoc();

        assert loc.getLocId() != null;
        assert loc.getLocName().equals("Test Loc");

        dao.delete(loc.getLocId());

        Location fromDao = dao.read(loc.getLocId());

        assert fromDao == null;
    }

    /**
     * Test of editLoc method, of class LocationDao.
     */
    @Test
    public void testEditLoc() {

        Location loc = createLoc();

        Location fromDaoFirst = dao.read(loc.getLocId());

        assert fromDaoFirst.getZip() == 10012;

        loc.setZip(11211);

        dao.update(loc);

        Location fromDaoSecond = dao.read(loc.getLocId());

        assert fromDaoSecond.getZip() == 11211;
    }

    /**
     * Test of getAllLoc method, of class LocationDao.
     */
    @Test
    public void testGetAllLoc() {

        Location loc = createLoc();

        List<Location> locListOne = dao.getAllLoc();

        assert locListOne.size() > 0;

    }

    /**
     * Test of getLoc method, of class LocationDao.
     */
    @Test
    public void getLocationListBySuperHuman() {

        SuperHuman superHuman = new SuperHuman();
        superHuman.setName("TestName");
        superHuman.setType("hero");
        superHuman.setDescription("TestDesc");
        superHuman.setSuperpower("TestPower");

        superHumanDao.create(superHuman);

        Location loc = new Location();
        loc.setLocName("Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        dao.create(loc);

        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));
        sighting.setLoc(loc);

        sightingDao.addSighting(sighting);

        SuperHumanSighting x = new SuperHumanSighting();
        x.setSuperHuman(superHuman);
        x.setSighting(sighting);

        superHumanSightingDao.create(x);

        List<Location> locList = dao.getLocationListBySuperHuman(superHuman);

        assert locList != null;
    }

    @Test
    public void getLocationListBySuperHumanTwo() {

        SuperHuman superHuman = new SuperHuman();
        superHuman.setName("TestName");
        superHuman.setType("hero");
        superHuman.setDescription("TestDesc");
        superHuman.setSuperpower("TestPower");

        superHumanDao.create(superHuman);

        Location loc = new Location();
        loc.setLocName("Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        dao.create(loc);

        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));
        sighting.setLoc(loc);

        sightingDao.addSighting(sighting);

        Location locTwo = new Location();
        locTwo.setLocName("Test Loc2");
        locTwo.setLocDesc("Test Loc Desc2");
        locTwo.setStreetAddress("2700 Malcom Bridge Rd");
        locTwo.setZip(10012);
        locTwo.setLat(33.914683);
        locTwo.setLon(-83.505634);

        dao.create(locTwo);

        Sighting sightingTwo = new Sighting();
        sightingTwo.setDateTime(LocalDateTime.parse("2018-02-01T00:00:00"));
        sightingTwo.setLoc(locTwo);

        sightingDao.addSighting(sightingTwo);

        SuperHumanSighting x = new SuperHumanSighting();
        x.setSuperHuman(superHuman);
        x.setSighting(sighting);

        superHumanSightingDao.create(x);

        SuperHumanSighting xTwo = new SuperHumanSighting();
        xTwo.setSuperHuman(superHuman);
        xTwo.setSighting(sightingTwo);

        superHumanSightingDao.create(xTwo);

        List<Location> locList = dao.getLocationListBySuperHuman(superHuman);

        assert locList != null;
    }

    private Location createLoc() {
        Location loc = new Location();
        loc.setLocName("Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        return dao.create(loc);
    }

}
