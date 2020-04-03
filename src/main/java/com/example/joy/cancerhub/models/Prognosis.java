package com.example.joy.cancerhub.models;

import com.google.firebase.Timestamp;

public class Prognosis {

    private String cancerDisease;
    private float riskScore ;
    private String riskLevel;
    private String recommendation;
    private Timestamp timestamp;


    public Prognosis() {
    }

    public Prognosis(String cancerDisease,float riskScore, String riskLevel, String recommendation, Timestamp timestamp) {
        this.cancerDisease = cancerDisease;
        this.riskLevel = riskLevel;
        this.riskScore = riskScore;
        this.recommendation = recommendation;
        this.timestamp = timestamp;
    }

    public Prognosis(String cancerDisease, float riskScore, String riskLevel, String recommendation) {
        this.cancerDisease = cancerDisease;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.recommendation = recommendation;
    }

    public String getCancerDisease() {
        return cancerDisease;
    }

    public void setCancerDisease(String cancerDisease) {
        this.cancerDisease = cancerDisease;
    }

    public float getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(float riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
