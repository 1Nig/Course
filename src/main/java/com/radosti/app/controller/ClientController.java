package com.radosti.app.controller;

import com.radosti.app.domain.Client;
import com.radosti.app.dto.ClientCreateRequest;
import com.radosti.app.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerClient(@RequestBody ClientCreateRequest request) {
        Client created = clientService.registerClient(request);
        return ResponseEntity
                .status(201)
                .body(Map.of("passportID", created.getPassportID()));
    }

    @GetMapping("/{passportID}")
    public ResponseEntity<Client> findClient(@PathVariable String passportID) {
        return ResponseEntity.ok(clientService.findById(passportID));
    }
}
