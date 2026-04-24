package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;

public class ApartmentRegister implements Command{
    private final ApartmentService apartmentService;
    public ApartmentRegister(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    public boolean matches(String[] parts){
    if(parts.length == 4 && parts[0].equalsIgnoreCase("apartment")&& parts[1].equalsIgnoreCase("register")){
        return true;
    }
    else{
    return false;}
    }
    public void execute(String[] parts){

            int id = Integer.parseInt(parts[2]);
            int price = Integer.parseInt(parts[3]);
            apartmentService.registerApartment(id, price);
    }

}
