package com.example.sasitha.rasa_motors.Quotation;

public class Quotations {
    private String vehicle_no;
    private String type;
    private String released_Date;
    private String jobs_Appointed;
    private String in_Date;
    private String damage_Description;
    private String customer_name;

    public Quotations(){

    }

    public Quotations(String vehicle_no, String type, String released_Date, String jobs_Appointed, String in_Date, String damage_Description, String customer_name) {
        this.vehicle_no = vehicle_no;
        this.type = type;
        this.released_Date = released_Date;
        this.jobs_Appointed = jobs_Appointed;
        this.in_Date = in_Date;
        this.damage_Description = damage_Description;
        this.customer_name = customer_name;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleased_Date() {
        return released_Date;
    }

    public void setReleased_Date(String released_Date) {
        this.released_Date = released_Date;
    }

    public String getJobs_Appointed() {
        return jobs_Appointed;
    }

    public void setJobs_Appointed(String jobs_Appointed) {
        this.jobs_Appointed = jobs_Appointed;
    }

    public String getIn_Date() {
        return in_Date;
    }

    public void setIn_Date(String in_Date) {
        this.in_Date = in_Date;
    }

    public String getDamage_Description() {
        return damage_Description;
    }

    public void setDamage_Description(String damage_Description) {
        this.damage_Description = damage_Description;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
