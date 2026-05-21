package com.radosti.app.repository;

import com.radosti.app.AbstractIntegrationTest;
import com.radosti.app.domain.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

public class ClientRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSaveAndFind() {
        Client client = new Client();
        client.setPassportID("1234");
        client.setName("John");
        client.setSurname("Test");

        clientRepository.save(client);

        Client found = clientRepository.findById("1234").orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("John");
    }
}
