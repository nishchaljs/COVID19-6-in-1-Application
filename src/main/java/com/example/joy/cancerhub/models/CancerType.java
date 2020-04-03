package com.example.joy.cancerhub.models;

public class CancerType {
    private String id;
    private String name;

    private String overview;
    private String medical_illustrations;
    private String risk_factors;
    private String signs_symptoms;
    private String diagnosis;
    private String treatement;

    public CancerType() {
    }

    public CancerType(String id, String name, String overview, String medical_illustrations, String risk_factors, String signs_symptoms, String diagnosis, String treatement) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.medical_illustrations = medical_illustrations;
        this.risk_factors = risk_factors;
        this.signs_symptoms = signs_symptoms;
        this.diagnosis = diagnosis;
        this.treatement = treatement;
    }

    public CancerType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getMedical_illustrations() {
        return medical_illustrations;
    }

    public void setMedical_illustrations(String medical_illustrations) {
        this.medical_illustrations = medical_illustrations;
    }

    public String getRisk_factors() {
        return risk_factors;
    }

    public void setRisk_factors(String risk_factors) {
        this.risk_factors = risk_factors;
    }

    public String getSigns_symptoms() {
        return signs_symptoms;
    }

    public void setSigns_symptoms(String signs_symptoms) {
        this.signs_symptoms = signs_symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatement() {
        return treatement;
    }

    public void setTreatement(String treatement) {
        this.treatement = treatement;
    }
}
