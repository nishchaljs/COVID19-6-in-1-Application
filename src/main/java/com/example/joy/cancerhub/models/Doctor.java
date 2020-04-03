package com.example.joy.cancerhub.models;

public class Doctor {

    private String name;
    private String description;
    private String exactPlace;
    private String picture;
    private String emailaddress;

    public Doctor(){}


    public Doctor(String emailaddress) {
        this.emailaddress = emailaddress;
    }
    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public Doctor(String name, String description, String exactPlace, String picture) {
        this.name = name;
        this.description = description;
        this.exactPlace = exactPlace;
        this.picture = picture;
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

    public String getExactPlace() {
        return exactPlace;
    }

    public void setExactPlace(String exactPlace) {
        this.exactPlace = exactPlace;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}

