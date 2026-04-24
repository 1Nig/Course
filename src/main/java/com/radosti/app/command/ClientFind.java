package com.radosti.app.command;

import com.radosti.app.service.ClientService;

public class ClientFind implements Command{
    private final ClientService clientService;
    public ClientFind(ClientService clientService){
        this.clientService = clientService;
    }
    @Override
    public boolean matches(String[] parts) {
        if(parts.length == 3 && parts[0].equalsIgnoreCase("client")&& parts[1].equalsIgnoreCase("find")){
            return true;
    }
    else{
            return false;
    }
}
    public void execute(String[] parts)
        {
        String passportId = parts[2];
        clientService.findById(passportId);
        }
}
