package com.radosti.app.domain;

public class Client {
    private String name;
    private String surname;
    private String passportID;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassportID() {
        return passportID;
    }
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Client(String name, String surname, String passportID){
        this.name = name;
        this.surname = surname;
        this.passportID = passportID;
    }
    public Client(String name, String passportID){
        this.name = name;
        this.passportID = passportID;
    }
}
