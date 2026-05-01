package com.radosti.app.service;

import com.radosti.app.config.AppConfig;
import com.radosti.app.domain.Apartment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ApartmentServiceTest {

    @Test
    void registerApartment_createNewApartment(){
    ClientService clientService = new ClientService();
    AppConfig appConfig = new AppConfig();
    ApartmentService apartmentService = new ApartmentService(clientService, appConfig);

    apartmentService.registerApartment(1, 100.0);
    Apartment a = apartmentService.findById(1);
    assertNotNull(a);
    assertEquals(100.0, a.getPrice());
    }

    @Test
    void findById_SearchingApartment(){
        ClientService clientService = new ClientService();
        AppConfig appConfig = new AppConfig();
        ApartmentService apartmentService = new ApartmentService(clientService, appConfig);
        apartmentService.registerApartment(1, 100.0);

        assertNotNull(apartmentService.findById(1));
        assertNull(apartmentService.findById(77));
    }

    @Test
    void reserveApartment_setReserveTrue(){
        ClientService clientService = new ClientService();
        AppConfig appConfig = new AppConfig();
        ApartmentService apartmentService = new ApartmentService(clientService, appConfig);
        apartmentService.registerApartment(1, 100.0);
        clientService.registerClient("12345", "John", "Johnson");

        apartmentService.reserveApartment(1, "12345");
        Apartment a = apartmentService.findById(1);
        assertNotNull(a.getClient());
        assertTrue(a.isReserved());
    }

    @Test
    void releaseApartment_setReserveFalse(){
        ClientService clientService = new ClientService();
        AppConfig appConfig = new AppConfig();
        ApartmentService apartmentService = new ApartmentService(clientService, appConfig);
        apartmentService.registerApartment(1, 100.0);
        clientService.registerClient("12345", "John", "Johnson");
        apartmentService.reserveApartment(1, "12345");

        apartmentService.releaseApartment(1);
        Apartment a = apartmentService.findById(1);
        assertFalse(a.isReserved());
        assertNull(a.getClient());
    }

     @Test
    void listApartment_checkingPagination(){
         ClientService clientService = new ClientService();
         AppConfig appConfig = new AppConfig();
         ApartmentService apartmentService = new ApartmentService(clientService, appConfig);
         apartmentService.registerApartment(1, 100.0);
         apartmentService.registerApartment(3, 200.0);
         apartmentService.registerApartment(7, 300.0);
         apartmentService.registerApartment(4, 400.0);
         apartmentService.registerApartment(2, 500.0);

         List<Apartment> sortedByPrice = apartmentService.listApartments(1, 3 , "price");
         List<Apartment> sortedById = apartmentService.listApartments(1,5,"id");
         List<Apartment> shouldBeEmpty = apartmentService.listApartments(2, 10, "price");

         assertEquals(3, sortedByPrice.size());
         assertEquals(100.0, sortedByPrice.get(0).getPrice());
         assertEquals(200.0, sortedByPrice.get(1).getPrice());
         assertEquals(300.0, sortedByPrice.get(2).getPrice());


         assertEquals(5, sortedById.size());
         assertEquals(1, sortedById.get(0).getId());
         assertEquals(2, sortedById.get(1).getId());
         assertEquals(3, sortedById.get(2).getId());
         assertEquals(4, sortedById.get(3).getId());
         assertEquals(7, sortedById.get(4).getId());

         assertTrue(shouldBeEmpty.isEmpty());

     }
}
