package com.example.sasitha.rasa_motors.Appointments;

public class AppointmentNew {
    private String vehicle;
    private  String job;
    private String date;

    public AppointmentNew() {
    }

    public AppointmentNew(String vehicle, String job, String date) {
        this.vehicle = vehicle;
        this.job = job;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
