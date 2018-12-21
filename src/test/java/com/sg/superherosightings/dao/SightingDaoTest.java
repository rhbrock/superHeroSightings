/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class SightingDaoTest {

    private SightingDao dao;

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("sightingDao", SightingDao.class);
    }

    /**
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddSighting() {

        Sighting sighting = createLocAndSighting();

        String ldt = sighting.getDateTime().toString();

        assert sighting.getSightingId() != null;
        assert ldt.equals("2018-01-01T00:00");
        assert sighting.getLoc().getLocName().equals("Test Loc");

    }

    @Test
    public void testAddSightingNoOrg() {

        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));

        dao.addSighting(sighting);

        String ldt = sighting.getDateTime().toString();

        assert sighting.getSightingId() != null;
        assert ldt.equals("2018-01-01T00:00");
        //assert sighting.getLoc().getLocName().equals("Test Loc");

    }

    /**
     * Test of deleteSighting method, of class SightingDao.
     */
    @Test
    public void testDeleteSighting() {

        Sighting sighting = createLocAndSighting();

        String ldt = sighting.getDateTime().toString();

        assert sighting.getSightingId() != null;
        assert ldt.equals("2018-01-01T00:00");
        assert sighting.getLoc().getLocName().equals("Test Loc");

        dao.deleteSighting(sighting.getSightingId());

        Sighting fromDao = dao.getSighting(sighting.getSightingId());

        assert fromDao == null;

    }

    /**
     * Test of editSighting method, of class SightingDao.
     */
    @Test
    public void testEditSighting() {
        Sighting sighting = createLocAndSighting();

        String ldt = sighting.getDateTime().toString();

        assert sighting.getSightingId() != null;
        assert ldt.equals("2018-01-01T00:00");
        assert sighting.getLoc().getLocName().equals("Test Loc");

        sighting.setDateTime(LocalDateTime.parse("2019-01-01T00:00:00"));

        dao.editSighting(sighting);

        Sighting fromDao = dao.getSighting(sighting.getSightingId());

        ldt = sighting.getDateTime().toString();

        assert ldt.equals("2019-01-01T00:00");

    }

    /**
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testGetAllSightings() {
        Sighting sighting = createLocAndSighting();

        List<Sighting> list = dao.getAllSightings();

        assert list.size() > 0;
    }

    /**
     * Test of getSighting method, of class SightingDao.
     */
    @Test
    public void testGetSightingNoOrg() {

        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));

        dao.addSighting(sighting);

        Sighting fromDao = dao.getSighting(sighting.getSightingId());
        
        assert sighting.getLoc() == null;

    }

    /**
     * Test of getSightingsBySuperHuman method, of class SightingDao.
     */
    @Test
    public void testGetSightingsBySuperHuman() {
    }

    /**
     * Test of getSightingsAtLocation method, of class SightingDao.
     */
    @Test
    public void testGetSightingsAtLocation() {
    }

    private Sighting createLocAndSighting() {

        Location loc = new Location();
        loc.setLocId(1L);
        loc.setLocName("Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.parse("2018-01-01T00:00:00"));
        sighting.setLoc(loc);

        return dao.addSighting(sighting);
    }

}
