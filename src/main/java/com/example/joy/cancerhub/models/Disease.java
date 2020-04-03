package com.example.joy.cancerhub.models;

import java.util.List;

public class Disease  {

    private String name;
    private List<String> symptomids;
    private int count = 0;

    public Disease() {
    }

    public Disease(String name, List<String> symptomids) {
        this.name = name;
        this.symptomids = symptomids;
    }
    public Disease(String name){
        this.name = name;
    }

    public List<String> getSymptomids() {
        return symptomids;
    }

    public void setSymptomids(List<String> symptomids) {
        this.symptomids = symptomids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
