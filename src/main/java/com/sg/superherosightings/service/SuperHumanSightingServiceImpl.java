/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.dao.SuperHumanDao;
import com.sg.superherosightings.dao.SuperHumanSightingDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import com.sg.superherosightings.model.SuperHumanSighting;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanSightingServiceImpl implements SuperHumanSightingService {

    SuperHumanSightingDao dao;
    SuperHumanDao superHumanDao;
    SightingDao sightingDao;
    LocationDao locDao;
    SuperHeroAuditDao auditDao;

    @Inject
    public SuperHumanSightingServiceImpl(SuperHumanSightingDao dao,
            SuperHumanDao superHumanDao, SightingDao sightingDao,
            LocationDao locDao, SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.superHumanDao = superHumanDao;
        this.sightingDao = sightingDao;
        this.locDao = locDao;
        this.auditDao = auditDao;
    }

    @Override
    public SuperHumanSighting create(SuperHumanSighting superHumanSighting) {
        return dao.create(superHumanSighting);
    }

    @Override
    public SuperHumanSighting read(Long id) {

        SuperHumanSighting superHumanSighting = dao.read(id);

        SuperHuman superHuman = superHumanDao.read(superHumanSighting.getSuperHuman().getSuperId());
        superHumanSighting.setSuperHuman(superHuman);

        Sighting sighting = sightingDao.getSighting(superHumanSighting.getId());
        sighting.setLoc(superHumanSighting.getSighting().getLoc());
        superHumanSighting.setSighting(sighting);

        return superHumanSighting;
    }

    @Override
    public SuperHumanSighting update(SuperHumanSighting superHumanSighting) {
        return dao.update(superHumanSighting);
    }

    @Override
    public void delete(Long id) {

//        SuperHumanSighting superHumanSighting = dao.read(id);
//
//        superHumanDao.delete(superHumanSighting.getSuperHuman().getSuperId());
//
//        sightingDao.deleteSighting(superHumanSighting.getSighting().getSightingId());
        dao.delete(id);
    }

    @Override
    public List<SuperHumanSighting> getSuperHumanSightingBySighting(Sighting sighting) {

        List<SuperHumanSighting> list = dao.getSuperHumanSightingBySighting(sighting);

        for (SuperHumanSighting current : list) {
            current.setSuperHuman(superHumanDao.read(current.getSuperHuman().getSuperId()));

            Sighting sightingInLoop = sightingDao.getSighting(current.getSighting().getSightingId());

            Location loc = locDao.read(sightingInLoop.getLoc().getLocId());
            sightingInLoop.setLoc(loc);

            current.setSighting(sightingInLoop);
        }

        return list;
    }

    @Override
    public List<SuperHumanSighting> getSuperHumanSightingBySuperHuman(SuperHuman superHuman) {

        List<SuperHumanSighting> list = dao.getSuperHumanSightingBySuperHuman(superHuman);

        for (SuperHumanSighting current : list) {
            current.setSuperHuman(superHumanDao.read(current.getSuperHuman().getSuperId()));

            Sighting sightingInLoop = sightingDao.getSighting(current.getSighting().getSightingId());

            Location loc = locDao.read(sightingInLoop.getLoc().getLocId());
            sightingInLoop.setLoc(loc);

            current.setSighting(sightingInLoop);
        }
        return list;
    }

//    SuperHumanSightingDao dao;
//    SuperHeroAuditDao auditDao;
//
//    @Inject
//    public SuperHumanSightingServiceImpl(SuperHumanSightingDao dao, SuperHeroAuditDao auditDao) {
//        this.dao = dao;
//        this.auditDao = auditDao;
//    }
//
//    @Override
//    public SuperHumanSighting create(SuperHumanSighting superHumanSighting) {
//        return dao.create(superHumanSighting);
//    }
//
//    @Override
//    public SuperHumanSighting read(Long id) {
//        return dao.read(id);
//    }
//
//    @Override
//    public SuperHumanSighting update(SuperHumanSighting superHumanSighting) {
//        return dao.update(superHumanSighting);
//    }
//
//    @Override
//    public void delete(Long id) {
//        dao.delete(id);
//    }
//
//    @Override
//    public List<SuperHumanSighting> getSuperHumanSightingBySighting(Sighting sighting) {
//        return dao.getSuperHumanSightingBySighting(sighting);
//    }
//
//    @Override
//    public List<SuperHumanSighting> getSuperHumanSightingBySuperHuman(SuperHuman superHuman) {
//        return dao.getSuperHumanSightingBySuperHuman(superHuman);
//    }
//    @Override
//    public List<SuperHumanSighting> getSuperHumanSightingsForDisplay(List<Sighting> sightings) {
//
//        List<SuperHumanSighting> list = new ArrayList<>();
//
//        for (Sighting current : sightings) {
//            list.addAll(getSuperHumanSightingBySighting(current));
//        }
//
//        return list;
//    }
}
