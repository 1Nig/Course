package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApartmentReserveTest {
    @Test
    void ApartmentReserve_correctMatches() {
        String[] parts ={"apartment", "reserve", "12", "100fhv"};
        String[] parts0 ={"Apartment", "Reserve", "12", "100ghy"};
        String[] parts1 ={"smth", "reserve", "12", "100hg"};
        String[] parts2 ={"apartment", "12"};
        String[] parts3 ={"apartment", "reserve", "12", "112", "smthElse"};

        ApartmentReserve command = new ApartmentReserve(null);

        assertTrue(command.matches(parts));
        assertTrue(command.matches(parts0));
        assertFalse(command.matches(parts1));
        assertFalse(command.matches(parts2));
        assertFalse(command.matches(parts3));
    }
}