package com.example.sasitha.rasa_motors.customers;

public class customer
{
    private String Name;
    private String Address;
    private String Phone;

    public customer() {
    }

    public customer(String name, String address, String phone) {
        Name = name;
        Address = address;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
