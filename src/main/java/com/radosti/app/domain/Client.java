package com.radosti.app.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "clients")
public class Client {
    private String name;
    private String surname;
    @Id
    @Column(name = "passport_id")
    private String passportID;
    @OneToMany(mappedBy = "client")
    private List<Apartment> apartments;

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
    public Client(String passportID, String name, String surname){
        this.name = name;
        this.surname = surname;
        this.passportID = passportID;
    }
    public Client(String passportID, String name){
        this.name = name;
        this.passportID = passportID;
    }
    public Client(){}
}
