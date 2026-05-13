package com.radosti.app.command;

import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApartmentReleaseTest {
    @Test
    void ApartmentRelease_correctMatches() {
        String[] parts ={"apartment", "release", "12"};
        String[] parts0 ={"Apartment", "Release", "12"};
        String[] parts1 ={"smth", "release", "12"};
        String[] parts2 ={"find", "12"};
        String[] parts3 ={"apartment", "release", "12", "112"};

        ApartmentRelease command = new ApartmentRelease(null);

        assertTrue(command.matches(parts));
        assertTrue(command.matches(parts0));
        assertFalse(command.matches(parts1));
        assertFalse(command.matches(parts2));
        assertFalse(command.matches(parts3));
    }
}