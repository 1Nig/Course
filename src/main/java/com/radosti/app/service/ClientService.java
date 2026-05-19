package com.radosti.app.service;

import com.radosti.app.domain.Client;
import com.radosti.app.dto.ClientCreateRequest;
import com.radosti.app.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerClient(ClientCreateRequest request) {

        if (clientRepository.existsById(request.passportID())) {
            throw new RuntimeException("Client already exists");
        }

        Client client = new Client(
                request.passportID(),
                request.name(),
                request.surname()
        );

        return clientRepository.save(client);
    }

    public Client findById(String passportID) {
        return clientRepository.findById(passportID)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
