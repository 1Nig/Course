package com.radosti.app.app;

import com.radosti.app.command.*;
import com.radosti.app.config.AppConfig;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ClientService clientService = new ClientService();
        ApartmentService apartmentService = new ApartmentService(clientService, appConfig);
        List<Command> commands = new ArrayList<>();
        ApartmentFind apartmentFind = new ApartmentFind(apartmentService);
        commands.add(apartmentFind);
        ApartmentRegister apartmentRegister = new ApartmentRegister(apartmentService);
        commands.add(apartmentRegister);
        ApartmentRelease apartmentRelease = new ApartmentRelease(apartmentService);
        commands.add(apartmentRelease);
        ApartmentReserve apartmentReserve = new ApartmentReserve(apartmentService);
        commands.add(apartmentReserve);
        ClientFind clientFind = new ClientFind(clientService);
        commands.add(clientFind);
        ClientRegister clientRegister = new ClientRegister(clientService);
        commands.add(clientRegister);
        PrintListOfApartments printListOfApartments = new PrintListOfApartments(apartmentService);
        commands.add(printListOfApartments);
        Exit exit = new Exit();
        commands.add(exit);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(">");
            String line = scanner.nextLine();
            String[] parts = line.trim().split("\\s+");
            boolean found = false;
            for(Command c:commands){
                if(c.matches(parts)){
                    found = true;
                    c.execute(parts);
                    break;
                }
            }
            if (!found) {
                System.out.println("Unknown command.");
            }
        }

    }
}