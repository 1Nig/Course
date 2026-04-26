package com.radosti.app.integration;

import com.radosti.app.command.ApartmentRegister;
import com.radosti.app.command.ApartmentReserve;
import com.radosti.app.command.ClientRegister;
import com.radosti.app.domain.Apartment;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class integrationTest {
    @Test
    void Test_ApartmentRegistation_And_MakingList(){
        ClientService clientService = new ClientService();
        ApartmentService apartmentService = new ApartmentService(clientService);

        ApartmentRegister ApRegister = new ApartmentRegister(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};

        ApRegister.execute(ap_register);

        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1,list.size());
        assertEquals(100.0,list.get(0).getPrice());
    }
    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve(){
        ClientService clientService = new ClientService();
        ApartmentService apartmentService = new ApartmentService(clientService);

        ApartmentRegister ApRegister = new ApartmentRegister(apartmentService);
        ClientRegister clientRegister = new ClientRegister(clientService);
        ApartmentReserve ApReserve = new ApartmentReserve(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};
        String[] client_register = {"client", "register", "AB123", "John", "Johnson"};
        String[] ap_reserve = {"apartment", "reserve", "1", "AB123"};

        ApRegister.execute(ap_register);
        clientRegister.execute(client_register);
        ApReserve.execute(ap_reserve);

    }
    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve_MakingList(){
        ClientService clientService = new ClientService();
        ApartmentService apartmentService = new ApartmentService(clientService);

        ApartmentRegister ApRegister = new ApartmentRegister(apartmentService);
        ClientRegister clientRegister = new ClientRegister(clientService);
        ApartmentReserve ApReserve = new ApartmentReserve(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};
        String[] client_register = {"client", "register", "AB123", "John", "Johnson"};
        String[] ap_reserve = {"apartment", "reserve", "1", "AB123"};

        ApRegister.execute(ap_register);
        clientRegister.execute(client_register);
        ApReserve.execute(ap_reserve);

        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1,list.size());
        assertTrue(list.get(0).isReserved());
        assertEquals("Johnson",list.get(0).getClient().getSurname());
    }
}
