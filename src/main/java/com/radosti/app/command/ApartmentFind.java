package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;

public class ApartmentFind implements Command{
    private final ApartmentService apartmentService;
    public ApartmentFind(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    public boolean matches(String[] parts){
        if (parts.length == 3 && parts[0].equalsIgnoreCase("apartment") && parts[1].equalsIgnoreCase("find")) {
            return true;
    }
        else{
        return false;}
    }
    public void execute(String[] parts){
        int id = Integer.parseInt(parts[2]);
        apartmentService.findById(id);
    }
}
