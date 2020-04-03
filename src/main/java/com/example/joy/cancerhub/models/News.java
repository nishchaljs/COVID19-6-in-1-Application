package com.example.joy.cancerhub.models;

import com.google.firebase.Timestamp;

public class News {

    private String title, medNews,newsLink;
    private Timestamp shortdesc;


    public News(){}
    public News(String title, Timestamp shortdesc, String medNews, String newsLink){
        this.title =title;
        this.shortdesc = shortdesc;
        this.medNews = medNews;
        this.newsLink = newsLink;
    }
    public String getTitle(){return title;}
    public void setTitle(String title){this.title =title;}

    public Timestamp getShortdesc() {
        return shortdesc;
    }

    public String getMedNews(){return medNews;}
    public void setMedNews(String medNews){this.medNews = medNews;}

    public String getNewsLink(){return newsLink;}
    public void setNewsLink(String newsLink){this.newsLink =newsLink;}


}
