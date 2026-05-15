package com.radosti.app.dao;

import com.radosti.app.domain.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClientDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Client client) {
        entityManager.persist(client);
    }
    @Transactional(readOnly = true)
    public Client findById(String passportID){
        return entityManager.find(Client.class, passportID);
    }
}


