package com.radosti.app.command;

import com.radosti.app.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientFindTest {
        @Test
        void ClientFind_correctMatches() {
            String[] parts ={"client", "find", "12"};
            String[] parts0 ={"Client", "Find", "12"};
            String[] parts1 ={"smth", "find", "12"};
            String[] parts2 ={"find", "12"};
            String[] parts3 ={"client", "find", "12", "112"};
            ClientService clientService = new ClientService();
            ClientFind command = new ClientFind(clientService);
            assertTrue(command.matches(parts));
            assertTrue(command.matches(parts0));
            assertFalse(command.matches(parts1));
            assertFalse(command.matches(parts2));
            assertFalse(command.matches(parts3));
        }
    }


