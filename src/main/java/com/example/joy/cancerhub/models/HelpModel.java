package com.example.joy.cancerhub.models;

public class HelpModel {

    private String title, helpInfo;


    public HelpModel() {
    }

    public HelpModel(String title, String helpInfo) {
        this.title = title;
        this.helpInfo = helpInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHelpInfo() {
        return helpInfo;
    }

    public void setHelpInfo(String helpInfo) {
        this.helpInfo = helpInfo;
    }
}
