package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApartmentRegisterTest {

    @Test
    void ApartmentRegister_correctMatches() {
        String[] parts ={"apartment", "register", "12", "100.0"};
        String[] parts0 ={"Apartment", "Register", "12", "100.0"};
        String[] parts1 ={"smth", "register", "12", "100.0"};
        String[] parts2 ={"apartment", "12"};
        String[] parts3 ={"apartment", "register", "12", "112", "smthElse"};

        ApartmentRegister command = new ApartmentRegister(null);

        assertTrue(command.matches(parts));
        assertTrue(command.matches(parts0));
        assertFalse(command.matches(parts1));
        assertFalse(command.matches(parts2));
        assertFalse(command.matches(parts3));
    }
}