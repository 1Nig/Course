package com.radosti.app.service;

import com.radosti.app.dao.ApartmentDAO;
import com.radosti.app.dao.ClientDAO;
import com.radosti.app.domain.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private ClientDAO clientDAO;
    public ClientService(ClientDAO clientDAO){
        this.clientDAO = clientDAO;
    }
public void registerClient(String passportID, String name, String surname){
    Client existing = clientDAO.findById(passportID);
    if(existing == null){
        Client client = new Client(passportID, name, surname);
        clientDAO.save(client);
        System.out.println("The registration is successfully finished!");
    }
    else{
        System.out.println("The client is already registred.");
    }
}

    public Client findById (String passportID){
    return clientDAO.findById(passportID);
    }
}
