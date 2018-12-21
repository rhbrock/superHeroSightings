/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

/**
 *
 * @author Roger Brock
 */
public class LocationExistsException extends Exception{

    public LocationExistsException(String message) {
        super(message);
    }

    public LocationExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
