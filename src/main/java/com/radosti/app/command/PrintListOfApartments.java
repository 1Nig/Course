package com.radosti.app.command;

import com.radosti.app.domain.Apartment;
import com.radosti.app.service.ApartmentService;

import java.util.List;

public class PrintListOfApartments implements Command {
    private final ApartmentService apartmentService;

    public PrintListOfApartments(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;}
        @Override
        public boolean matches (String[]parts){
            if (parts.length == 5 && parts[0].equalsIgnoreCase("apartment") && parts[1].equalsIgnoreCase("list")) {
                return true;
            } else {
                return false;
            }
        }
        public void execute (String[]parts){
            int page = Integer.parseInt(parts[2]);
            int size = Integer.parseInt(parts[3]);
            String sortBy = parts[4];
            List<Apartment> list = apartmentService.listApartments(page, size, sortBy);
            if (list.isEmpty()) {
                System.out.println("No apartments on this page.");
                return;
            }

            for (Apartment a : list) {
                String reserved = a.isReserved() ? "YES" : "NO";
                String clientName = a.getClient() != null ? a.getClient().getName() : "-";
                String passport = a.getClient() != null ? a.getClient().getPassportID() : "-";

                System.out.println(
                        a.getId() + " | " +
                                a.getPrice() + " | " +
                                reserved + " | " +
                                clientName + " | " +
                                passport
                );
            }

    }
}