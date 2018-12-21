/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Picture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roger Brock
 */
public class PictureDaoFileImpl implements PictureDao {

    private Map<Integer, Picture> pictureMap = new HashMap<>();

    private int pictureCounter = 0;

    @Override
    public Picture addPicture(Picture picture) throws PersistenceException {
        picture.setPicId(pictureCounter);
        pictureCounter++;
        pictureMap.put(picture.getPicId(), picture);
        return picture;
    }

    @Override
    public void deletePicture(int pictureId) throws PersistenceException {
        pictureMap.remove(pictureId);
    }

    @Override
    public Picture getPicture(int picId) {
        return pictureMap.get(picId);
    }

    @Override
    public List<Picture> getAllPictures() {
        List<Picture> pictureList = new ArrayList<>(pictureMap.values());
        return pictureList;
    }

//    private void writeImg() throws PersistenceException {
//
//    }
//
//    private void readImg() throws PersistenceException {
//
//    }

}
