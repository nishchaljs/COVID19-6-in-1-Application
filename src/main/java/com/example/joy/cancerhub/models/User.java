package com.example.joy.cancerhub.models;

public class User {

    public String fullname, username, email;
    public User(){}
    public User(String fullname,String username, String email){
        this.email = email;
        this.fullname = fullname;
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
