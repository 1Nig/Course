package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApartmentFindTest {
    @Test
    void ApartmentFind_correctMatches() {
        String[] parts ={"apartment", "find", "12"};
        String[] parts0 ={"Apartment", "Find", "12"};
        String[] parts1 ={"smth", "find", "12"};
        String[] parts2 ={"find", "12"};
        String[] parts3 ={"apartment", "find", "12", "112"};
        ClientService clientService = new ClientService();
        ApartmentService apartmentService = new ApartmentService(clientService);
        ApartmentFind command = new ApartmentFind(apartmentService);
        assertTrue(command.matches(parts));
        assertTrue(command.matches(parts0));
        assertFalse(command.matches(parts1));
        assertFalse(command.matches(parts2));
        assertFalse(command.matches(parts3));
    }
}
