package com.example.joy.cancerhub.models;

import android.media.Image;
import android.widget.ImageView;

public class Protect {

    private String title;
    private int img;


    public Protect() {
    }

    public Protect(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getimg() {
        return img;
    }

    public void setHelpInfo(String helpInfo) {
        this.img = img;
    }
}

