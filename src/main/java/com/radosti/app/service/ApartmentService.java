package com.radosti.app.service;

import com.radosti.app.dao.ApartmentDAO;
import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApartmentService {
    private ClientService clientService;
    private ApartmentDAO apartmentDAO;

    public ApartmentService(ApartmentDAO apartmentDAO, ClientService clientService){
        this.apartmentDAO = apartmentDAO;
        this.clientService = clientService;}


    public void registerApartment(int id, double price){
        Apartment existing = apartmentDAO.findById(id);
        if(existing == null){
            apartmentDAO.save(new Apartment(id, price));
        }
        else{
            System.out.println("The apartment with this id is already registred.");
        }
    }
    public Apartment findById(int id) {
        return apartmentDAO.findById(id);
    }


    public void reserveApartment(int id, String passportID){
        Apartment existing = apartmentDAO.findById(id);
        Client  client = clientService.findById(passportID);
        if(existing == null){
            System.out.println("Apartment is not found out.");

        }
        else if(client == null){
            System.out.println("The client is not found out.");}

        else  if (existing.isReserved()){
            System.out.println("Apartment is already reserved.");
        }
        else {
            existing.setReserved(true);
            existing.setClient(client);
            apartmentDAO.updateReservation(existing.getId(), passportID, existing.isReserved());
            System.out.println("The apartment is successfully reserved by "+ client.getName());
        }
    }
    public void releaseApartment(int id){
        Apartment existing = apartmentDAO.findById(id);
        if(existing == null){
            System.out.println("There's no apartment with this id.");
        }
        else if(!existing.isReserved()){
            System.out.println("The apartment wasn't reserved.");
        }
        else{
            existing.setReserved(false);
            existing.setClient(null);
            apartmentDAO.updateRelease(existing.getId(), existing.isReserved());
            System.out.println("The apartment is successfully released.");
        }

    }

    public List<Apartment> listApartments(int page, int size, String sortBy) {
        return apartmentDAO.findAll(page, size, sortBy);
    }


}
