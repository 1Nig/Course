package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;

public class ApartmentRelease implements Command{
    private final ApartmentService apartmentService;
    public ApartmentRelease(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }
    public boolean matches(String[] parts){
        if(parts.length == 3 && parts[0].equalsIgnoreCase("apartment")&&parts[1].equalsIgnoreCase("release")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void execute(String[] parts) {
        int id = Integer.parseInt(parts[3]);
        apartmentService.releaseApartment(id);
    }
}
