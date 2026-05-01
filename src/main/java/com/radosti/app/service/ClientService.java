package com.radosti.app.service;

import com.radosti.app.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
private List<Client> clients = new ArrayList<>();
public void registerClient(String passportID, String name, String surname){
    Client client = findById(passportID);
    if(client == null){
        client = new Client(passportID, name, surname);
        clients.add(client);
        System.out.println("The registration is successfully finished!");
    }
    else{
        System.out.println("The client is already registred.");
    }
}

    public Client findById (String passportID){
    return clients.stream()
                    .filter(c -> c.getPassportID().equals(passportID))
                            .findAny()
                                    .orElse(null);
}

}
