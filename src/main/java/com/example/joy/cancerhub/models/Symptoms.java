package com.example.joy.cancerhub.models;

public class Symptoms {
    private String id;
    private boolean isSelected;
    private String description;

    public Symptoms() {
    }

    public Symptoms(boolean isSelected, String description) {
        this.isSelected = isSelected;
        this.description = description;
    }

    public Symptoms(String id, boolean isSelected, String description) {
        this.id = id;
        this.isSelected = isSelected;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
