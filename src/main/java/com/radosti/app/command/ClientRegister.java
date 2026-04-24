package com.radosti.app.command;

import com.radosti.app.service.ClientService;

public class ClientRegister implements Command{
    private final ClientService clientService;
    public ClientRegister(ClientService clientService){
        this.clientService = clientService;
    }
    public boolean matches(String[] parts){
        if(parts.length == 5 && parts[0].equalsIgnoreCase("client")&& parts[1].equalsIgnoreCase("registrer")){
            return true;
        }
        else{
            return false;
        }
    }
    public void execute(String[] parts){
        String passportID = parts[2];
        String name = parts[3];
        String surname = parts[4];
        clientService.registerClient(passportID, name, surname);
    }
}

