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
public class SuperHumanExistsException extends Exception{

    public SuperHumanExistsException(String message) {
        super(message);
    }

    public SuperHumanExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
