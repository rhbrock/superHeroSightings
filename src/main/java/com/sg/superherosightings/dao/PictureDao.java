/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Picture;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface PictureDao {

    Picture addPicture(Picture picture)
            throws PersistenceException;

    void deletePicture(int picId)
            throws PersistenceException;
    
    Picture getPicture(int picId);

    List<Picture> getAllPictures();

}
