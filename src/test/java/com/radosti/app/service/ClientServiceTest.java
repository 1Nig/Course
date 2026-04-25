package com.radosti.app.service;

import com.radosti.app.domain.Client;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    @Test
    void registerClient_createNewClient(){
        ClientService clientService = new ClientService();

        clientService.registerClient("AB123", "John", "Johnson");
        Client a = clientService.findById("AB123");
        assertNotNull(a);
        assertEquals("John", a.getName());
        assertEquals("Johnson", a.getSurname());
        clientService.registerClient("AB123", "Michael", "Johnson");
        Client b = clientService.findById("AB123");
        assertNotEquals("Michael",b.getName());
    }

    @Test
    void findById_SearchingClient(){
        ClientService clientService = new ClientService();
        clientService.registerClient("AB123", "John", "Johnson");

        assertNotNull(clientService.findById("AB123"));
        assertNull(clientService.findById("bbkjfdbb52"));
    }
}
