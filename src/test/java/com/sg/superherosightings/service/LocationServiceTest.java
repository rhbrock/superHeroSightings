/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
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
public class LocationServiceTest {

    private LocationService service;
//    private SightingService sightingService;
//    private SuperHumanSightingService superHumanSightingService;

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("locationService", LocationService.class);

//        sightingService = ctx.getBean("sightingService", SightingService.class);
//        superHumanSightingService = ctx.getBean("superHumanSightingService", SuperHumanSightingService.class);
//        
//        List<Sighting> sightingList = sightingService.getAllSightings();
//        for(Sighting currentSighting : sightingList){
//            superHumanSightingService.delete(currentSighting.getSightingId());
//            sightingService.deleteSighting(currentSighting.getLoc().getLocId());
//        }
//        
//        List<Location> list = service.getAllLocations();
//        for(Location current : list){
//            service.deleteLocation(current.getLocId());
//        }
    }

    /**
     * Test of addNewLocation method, of class LocationService.
     */
    @Test
    public void testAddNewLocation() throws Exception {

        try {
            Location loc = create();

            service.addNewLocation(loc);

            Location fromDao = service.getLocationInfo(loc.getLocId());

            assert fromDao.getLocId() == loc.getLocId();

            Location locFail = create();

            service.addNewLocation(locFail);
            fail("Expected exception");
            
        } catch (LocationExistsException e) {
            return;
        }
    }

    private Location create() throws Exception {

        Location loc = new Location();
        loc.setLocName("Service Test Loc");
        loc.setLocDesc("Test Loc Desc");
        loc.setStreetAddress("2600 Malcom Bridge Rd");
        loc.setZip(10012);
        loc.setLat(33.914683);
        loc.setLon(-83.505634);

        return loc;
    }

}
