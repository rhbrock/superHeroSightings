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
public class SuperHumanSighting {
    
    Long id;
    SuperHuman superHuman;
    Sighting sighting;

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

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

  
}
