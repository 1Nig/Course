package com.radosti.app.integration;

import com.radosti.app.domain.Apartment;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Integration test — requires real DB")
@SpringBootTest
@Transactional
public class integrationTest {

    private static SessionFactory factory;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ClientService clientService;

    @Test
    void Test_ApartmentRegistration_And_MakingList() {
        apartmentService.registerApartment(1, 100.0);

        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1, list.size());
        assertEquals(100.0, list.get(0).getPrice());
        assertFalse(list.get(0).isReserved());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve() {
        apartmentService.registerApartment(1, 100.0);
        clientService.registerClient("AB123", "John", "Johnson" );

       apartmentService.reserveApartment(1, "AB123");

        Apartment a = apartmentService.findById(1);
        assertTrue(a.isReserved());
        assertEquals("AB123", a.getClient().getPassportID());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve_MakingList() {
        apartmentService.registerApartment(1, 100.0);
        clientService.registerClient("AB123", "John", "Johnson");
        apartmentService.reserveApartment(1, "AB123");


        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1, list.size());
        assertTrue(list.get(0).isReserved());
        assertEquals("Johnson", list.get(0).getClient().getSurname());
    }
}
