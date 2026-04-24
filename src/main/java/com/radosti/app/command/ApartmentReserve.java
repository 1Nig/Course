package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;

public class ApartmentReserve implements Command{
    private final ApartmentService apartmentService;
    public ApartmentReserve(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    public boolean matches(String[] parts){
        if(parts.length == 4 && parts[0].equalsIgnoreCase("apartment")&& parts[1].equalsIgnoreCase("reserve")){
            return true;
        }
        else{
            return false;
        }
    }
    public void execute(String[] parts){
        int id = Integer.parseInt(parts[2]);
        String passportID = parts[3];
        apartmentService.reserveApartment(id, passportID);
    }
}
