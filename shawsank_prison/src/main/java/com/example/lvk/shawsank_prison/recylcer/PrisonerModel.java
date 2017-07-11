package com.example.lvk.shawsank_prison.recylcer;

import android.media.Image;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by lavith.edam on 6/29/2017.
 */

public class PrisonerModel {

    int id;
    String name;
    String dob;
    String email;
    String mobile;
    String crime;
    Boolean isSentenced;
    String photoPath;
    String created_at;
    String upadated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public Boolean getSentenced() {
        return isSentenced;
    }

    public void setSentenced(Boolean sentenced) {
        isSentenced = sentenced;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpadated_at() {
        return upadated_at;
    }

    public void setUpadated_at(String upadated_at) {
        this.upadated_at = upadated_at;
    }
}
