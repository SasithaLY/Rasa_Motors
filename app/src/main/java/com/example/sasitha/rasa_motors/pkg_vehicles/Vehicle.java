package com.example.sasitha.rasa_motors.pkg_vehicles;

public class Vehicle {

    String vehModel;
    String vehType;
    String vehNumber;
    String vehColor;

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

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String getVehNumber() {
        return vehNumber;
    }

    public void setVehNumber(String vehNumber) {
        this.vehNumber = vehNumber;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }
}
