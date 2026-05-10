package com.radosti.app.command;

import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientRegisterTest {
    @Test
    void ApartmentRegister_correctMatches() {
        String[] parts ={"client", "register", "1212AN", "John", "Johnson"};
        String[] parts0 ={"Client", "Register", "1212AN", "John", "Johnson"};
        String[] parts1 ={"smth", "register", "1212AN", "John"};
        String[] parts2 ={"client", "12"};
        String[] parts3 ={"client", "find", "12", "John", "smthElse"};

        ClientRegister command = new ClientRegister(null);

        assertTrue(command.matches(parts));
        assertTrue(command.matches(parts0));
        assertFalse(command.matches(parts1));
        assertFalse(command.matches(parts2));
        assertFalse(command.matches(parts3));
    }
}