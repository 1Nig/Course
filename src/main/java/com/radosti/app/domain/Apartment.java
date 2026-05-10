package com.radosti.app.domain;

public class Apartment {
    private int id;
    private double price;
    private boolean isReserved;
    private Client client;
    private String client_passport;


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
    public void setClientPassport(String client_passport){ this.client_passport = client_passport;}
    public String getClient_passport(){return client_passport;}


    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
        if (client != null) {
            this.client_passport = client.getPassportID();
        } else {
            this.client_passport = null;
        }
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
    public Apartment(int id, double price, boolean isReserved, String client_passport){
        this.id = id;
        this.price = price;
        this.isReserved = isReserved;
        this.client_passport = client_passport;
    }
}
