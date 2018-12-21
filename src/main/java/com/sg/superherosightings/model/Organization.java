/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Roger Brock
 */
public class Organization {

    private Long orgId;
    @NotEmpty(message = "What is the name of this organization?")
    private String name;
    private String description;
    private String streetAddress;
    private int zip;
    @Email(message = "Please enter a valid email address.")
    private String email;
    @Length(min=10, max = 10, message = "Phone must be no more than 10 characters in length.")
    private String phoneNumber;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Organization{" + "orgId=" + orgId
                + ", name=" + name
                + ", description=" + description
                + ", streetAddress=" + streetAddress
                + ", zip=" + zip
                + ", email=" + email
                + ", phoneNumber=" + phoneNumber + '}';
    }

}
