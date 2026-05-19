package com.radosti.app.integration;

import com.radosti.app.domain.Apartment;
import com.radosti.app.dto.ApartmentCreateRequest;
import com.radosti.app.dto.ClientCreateRequest;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Integration test — requires real DB")
@SpringBootTest
@Transactional
public class integrationTest {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ClientService clientService;

    @Test
    void Test_ApartmentRegistration_And_MakingList() {

        apartmentService.registerApartment(new ApartmentCreateRequest(1, 100.0));

        Page<Apartment> page = apartmentService.listApartments(PageRequest.of(0, 1));

        assertEquals(1, page.getTotalElements());
        Apartment a = page.getContent().get(0);

        assertEquals(100.0, a.getPrice());
        assertFalse(a.isReserved());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve() {

        apartmentService.registerApartment(new ApartmentCreateRequest(1, 100.0));
        clientService.registerClient(new ClientCreateRequest("AB123", "John", "Johnson"));

        apartmentService.reserveApartment(1, "AB123");

        Apartment a = apartmentService.findById(1);

        assertTrue(a.isReserved());
        assertEquals("AB123", a.getClient().getPassportID());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve_MakingList() {

        apartmentService.registerApartment(new ApartmentCreateRequest(1, 100.0));
        clientService.registerClient(new ClientCreateRequest("AB123", "John", "Johnson"));
        apartmentService.reserveApartment(1, "AB123");

        Page<Apartment> page = apartmentService.listApartments(PageRequest.of(0, 1));

        assertEquals(1, page.getTotalElements());
        Apartment a = page.getContent().get(0);

        assertTrue(a.isReserved());
        assertEquals("Johnson", a.getClient().getSurname());
    }
}
