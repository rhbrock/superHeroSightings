/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SuperHeroAuditDao;
import com.sg.superherosightings.dao.SuperHumanDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperHuman;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanServiceImpl implements SuperHumanService {

    SuperHumanDao dao;
    private SuperHeroAuditDao auditDao;

    @Inject
    public SuperHumanServiceImpl(SuperHumanDao dao,
            SuperHeroAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public SuperHuman createNewSuperHuman(SuperHuman superHuman)
            throws SuperHumanExistsException, SuperHumanTypeException {

        List<SuperHuman> list = dao.getAll();

        for (SuperHuman x : list) {
            if (superHuman.getName().equalsIgnoreCase(x.getName())) {
                throw new SuperHumanExistsException("Super Human you are trying"
                        + " to add already exists.  "
                        + "Please select super human from provided list.");
            }
        }

        checkType(superHuman);

        return dao.create(superHuman);
    }

    @Override
    public void removeSuperHuman(Long superId) {
        dao.delete(superId);
    }

    @Override
    public SuperHuman editExisitingSuperHuman(SuperHuman superHuman)
            throws SuperHumanTypeException {

        checkType(superHuman);

        return dao.update(superHuman);
    }

    @Override
    public SuperHuman getSuperHumanInfo(Long superId) {
        return dao.read(superId);
    }

    @Override
    public List<SuperHuman> getAllSuperHumans() {
        return dao.getAll();
    }

    //validates user input for "type" of Super Human
    private void checkType(SuperHuman superHuman)
            throws SuperHumanTypeException {

        String typeOf = superHuman.getType();

        switch (typeOf) {
            case "hero":
                break;
            case "villain":
                break;
            default:
                throw new SuperHumanTypeException("Please select valid"
                        + "Super Human type of \"hero\" or \"villan\"");
        }
    }

    @Override
    public List<SuperHuman> getSuperHumansBySighting(Sighting sighting) {
        return dao.getSuperHumansBySighting(sighting);
    }

    @Override
    public List<SuperHuman> getSuperHumansByOrganization(Organization org) {
        return dao.getSuperHumansByOrganization(org);
    }

    @Override
    public List<SuperHuman> getHeros() {
        List<SuperHuman> list = dao.getAll();

        List<SuperHuman> hero = new ArrayList<>();

        for (SuperHuman current : list) {
            if (current.getType().equals("hero")) {
                hero.add(current);
            }
        }
        return hero;
    }

    @Override
    public List<SuperHuman> getVillains() {
        List<SuperHuman> list = dao.getAll();

        List<SuperHuman> villain = new ArrayList<>();

        for (SuperHuman current : list) {
            if (current.getType().equals("villain")) {
                villain.add(current);
            }
        }
        return villain;
    }

}
