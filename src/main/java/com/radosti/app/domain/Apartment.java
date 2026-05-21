package com.radosti.app.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Apartment {
    @Id
    private int id;
    private double price;
    private boolean isReserved;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isReserved() {
        return isReserved;
    }
    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }


    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }


    public Apartment(int id, double price, boolean isReserved){
        this.id = id;
        this.price = price;
        this.isReserved = isReserved;
    }
    public Apartment(int id, double price){
        this.id = id;
        this.price = price;
        this.isReserved = false;
    }
    public Apartment() {}

}
