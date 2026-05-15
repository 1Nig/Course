package com.radosti.app.controller;

import com.radosti.app.domain.Client;
import com.radosti.app.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public String registerClient(@RequestBody Client client) {
        clientService.registerClient(client.getPassportID(), client.getName(), client.getSurname());
        return "Client registered successfully";
    }

    @GetMapping("/{passportID}")
    public Client findClient(@PathVariable String passportID) {
        return clientService.findById(passportID);
    }
}
