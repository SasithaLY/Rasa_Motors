package com.example.sasitha.rasa_motors;

public class Vehicle {

    private String vehModel;
    private String vehType;
    private String vehNumber;
    private String vehColor;

    public Vehicle(){

    }

    public Vehicle(String vehModel, String vehType, String vehNumber, String vehColor) {
        this.vehModel = vehModel;
        this.vehType = vehType;
        this.vehNumber = vehNumber;
        this.vehColor = vehColor;
    }

    public String getVehModel() {

        return vehModel;
    }

    public String getVehType() {

        return vehType;
    }

    public String getVehNumber() {

        return vehNumber;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public void setVehNumber(String vehNumber) {
        this.vehNumber = vehNumber;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }
}
