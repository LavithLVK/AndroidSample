package com.example.lvk.sqlite_sample;

/**
 * Created by lavith.edam on 6/16/2017.
 */

public class Prisoner {

    int id;
    String name;
    int age;
    String crime;
    Boolean isSentenced;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
