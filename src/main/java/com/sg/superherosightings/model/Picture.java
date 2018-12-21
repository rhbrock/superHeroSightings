/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Roger Brock
 */
public class Picture {
    
    private int picId;
    private String picTitle;
    private String filename;
    private byte[] picture;

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.picId;
        hash = 61 * hash + Objects.hashCode(this.picTitle);
        hash = 61 * hash + Objects.hashCode(this.filename);
        hash = 61 * hash + Arrays.hashCode(this.picture);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Picture other = (Picture) obj;
        if (this.picId != other.picId) {
            return false;
        }
        if (!Objects.equals(this.picTitle, other.picTitle)) {
            return false;
        }
        if (!Objects.equals(this.filename, other.filename)) {
            return false;
        }
        if (!Arrays.equals(this.picture, other.picture)) {
            return false;
        }
        return true;
    }

}
