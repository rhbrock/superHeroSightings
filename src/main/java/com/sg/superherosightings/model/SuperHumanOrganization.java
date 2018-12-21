/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;

/**
 *
 * @author Roger Brock
 */
public class SuperHumanOrganization {
    
    Long id;
    SuperHuman superHuman;
    Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SuperHuman getSuperHuman() {
        return superHuman;
    }

    public void setSuperHuman(SuperHuman superHuman) {
        this.superHuman = superHuman;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
