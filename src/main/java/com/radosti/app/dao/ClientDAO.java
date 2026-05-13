package com.radosti.app.dao;

import com.radosti.app.domain.Client;
import com.radosti.app.config.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO {

            public void save(Client client) {

                try (Session session = Hibernate.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    session.persist(client);
                    tx.commit();

                }
            }
        public Client findById(String passportID){
            try (Session session = Hibernate.getSessionFactory().openSession()) {
                return session.find(Client.class, passportID);
            }

        }
    }


