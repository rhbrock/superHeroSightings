/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Roger Brock
 */
public class Location {

    private Long locId;
    @NotEmpty(message = "What is this location's name?")
    private String locName;
    private String locDesc;
    private String streetAddress;
    //@NotEmpty(message = "Must have zip")
    //@Range(min = 5, max = 5, message = "Enter valid zip.")
    private int zip;
    //@NotEmpty(message = "Must have lat")
    //@Range(min = 6, max = 6, message = "Enter valid coordinate (decimal degrees).")
    private double lat;
    //@NotEmpty(message = "Must have lon")
    //@Range(min = 6, max = 6, message = "Enter valid coordinate (decimal degrees).")
    private double lon;

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocDesc() {
        return locDesc;
    }

    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" + "locId=" + locId
                + ", locName=" + locName
                + ", locDesc=" + locDesc
                + ", streetAddress=" + streetAddress
                + ", zip=" + zip
                + ", lat=" + lat
                + ", lon=" + lon + '}';
    }

}
