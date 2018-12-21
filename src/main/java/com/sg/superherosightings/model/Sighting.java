/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Roger Brock
 */
public class Sighting {

    private Long sightingId;
    private LocalDateTime dateTime;
    private Location loc;
    //private Picture pic;

    public Long getSightingId() {
        return sightingId;
    }

    public void setSightingId(Long sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    

    @Override
    public String toString() {
        return "Sighting{" + "sightingId=" + sightingId 
                + ", dateTime=" + dateTime 
                + ", loc=" + loc + '}';
    }

   
}
