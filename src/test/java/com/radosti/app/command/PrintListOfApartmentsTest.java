package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrintListOfApartmentsTest {
        @Test
        void PrintListOfApartmentsTest_correctMatches() {
            String[] parts ={"apartment", "list", "1212", "1254", "price"};
            String[] parts0 ={"Apartment", "List", "1212", "1254", "PRice"};
            String[] parts1 ={"smth", "List", "1212", "PRice"};
            String[] parts2 ={"apartment", "12"};
            String[] parts3 ={"apartment", "List", "12", "1254", "price", "smthElse"};
            ClientService clientService = new ClientService();
            ApartmentService apartmentService = new ApartmentService(clientService);
            PrintListOfApartments command = new PrintListOfApartments(apartmentService);
            assertTrue(command.matches(parts));
            assertTrue(command.matches(parts0));
            assertFalse(command.matches(parts1));
            assertFalse(command.matches(parts2));
            assertFalse(command.matches(parts3));

    }
}
