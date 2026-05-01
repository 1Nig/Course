package com.radosti.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radosti.app.config.AppConfig;
import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApartmentService {
    ObjectMapper mapper = new ObjectMapper();
    private ClientService clientService;
    private AppConfig appConfig;
    public ApartmentService(ClientService clientService, AppConfig appConfig){
    this.clientService = clientService;
    this.appConfig = appConfig;
    loadState();
    }

    private List<Apartment> apartments = new ArrayList<>();
    public void registerApartment(int id, double price){
        Apartment apartment = findById(id);
        if(apartment == null){
            apartment = new Apartment(id, price);
            apartments.add(apartment);
            saveState();
        }
        else{
            System.out.println("The apartment with this id is already registred.");
        }
    }
    private void saveState() {
        try{
            mapper.writeValue(new File(appConfig.getDataFile()), apartments);
        }
        catch(IOException e){
            System.out.println("Error saving state: " + e.getMessage());
        }
    }
    private void loadState() {
        try{
            File file = new File(appConfig.getDataFile());
            if(file.exists()){
                apartments = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Apartment.class));
            }
        } catch (IOException e){
            System.out.println("Error loading state: " + e.getMessage());
        }
    }
    public Apartment findById (int id){
        return apartments.stream()
            .filter(a -> a.getId()==id)
            .findAny()
            .orElse(null);
    }

    public void reserveApartment(int id, String passportID){
        Apartment apartment = findById(id);
        Client  client = clientService.findById(passportID);
        if(apartment == null){
            System.out.println("Apartment is not found out.");
        }
        else if(client == null){
            System.out.println("The client is not found out.");}
        else if(appConfig.allowStatusChange() == false){
            System.out.println("The status of the appartment shouldn't be changed.");
        }
        else  if (apartment.isReserved()){
            System.out.println("Apartment is already reserved.");
        }
        else {
            apartment.setReserved(true);
            apartment.setClient(client);
            saveState();
            System.out.println("The apartment is successfully reserved by "+ client.getName());
        }
    }
    public void releaseApartment(int id){
        Apartment apartment = findById(id);
        if(apartment == null){
            System.out.println("There's no apartment with this id.");
        }
        else if(appConfig.allowStatusChange() == false){
            System.out.println("The status of the appartment shouldn't be changed.");
        }
        else if(apartment.isReserved()==false){
            System.out.println("The apartment wasn't reserved.");
        }
        else{
            apartment.setReserved(false);
            apartment.setClient(null);
            saveState();
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
