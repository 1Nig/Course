package com.radosti.app.service;

import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApartmentService {
    private ClientService clientService;
    public ApartmentService(ClientService clientService){
    this.clientService = clientService;}

    private List<Apartment> apartments = new ArrayList<>();
    public void registerApartment(int id, double price){
        Apartment apartment = findById(id);
        if(apartment == null){
            apartment = new Apartment(id, price);
            apartments.add(apartment);
        }
        else{
            System.out.println("The apartment with this id is already registred.");
        }
    }
    public Apartment findById (int id){
        for (Apartment a : apartments) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void reserveApartment(int id, String passportID){
        Apartment apartment = findById(id);
        Client  client = clientService.findById(passportID);
        if(apartment == null){
            System.out.println("Apartment is not found out.");

        }
        else if(client == null){
            System.out.println("The client is not found out.");}

        else  if (apartment.isReserved()){
            System.out.println("Apartment is already reserved.");
        }
        else {
            apartment.setReserved(true);
            apartment.setClient(client);
            System.out.println("The apartment is successfully reserved by "+ client.getName());
        }
    }
    public void releaseApartment(int id){
        Apartment apartment = findById(id);
        if(apartment == null){
            System.out.println("There's no apartment with this id.");
        }
        else if(apartment.isReserved()==false){
            System.out.println("The apartment wasn't reserved.");
        }
        else{
            apartment.setReserved(false);
            apartment.setClient(null);
            System.out.println("The apartment is successfully released.");
        }

    }

    public List<Apartment> listApartments(int page, int size, String sortBy) {
        if (sortBy.equals("price")) {
            apartments.sort(Comparator.comparingDouble(Apartment::getPrice));
        } else if (sortBy.equals("id")) {
            apartments.sort(Comparator.comparingInt(Apartment::getId));
        }

        int from = (page - 1) * size;
        int to = Math.min(from + size, apartments.size());

        if (from >= apartments.size()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(apartments.subList(from, to));

    }

}
