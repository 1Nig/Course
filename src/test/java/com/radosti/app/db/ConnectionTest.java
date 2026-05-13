package com.radosti.app.db;

import com.radosti.app.config.Hibernate;
import com.radosti.app.domain.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

    @Test
    void testHibernateSessionFactory() {
        SessionFactory factory = Hibernate.getSessionFactory();
        Assertions.assertNotNull(factory, "SessionFactory must not be null");
    }

    @Test
    void testOpenSession() {
        SessionFactory factory = Hibernate.getSessionFactory();
        try (Session session = factory.openSession()) {
            Assertions.assertTrue(session.isOpen(), "Session must be open");
        }
    }

    @Test
    void testSaveAndLoadEntity() {
        SessionFactory factory = Hibernate.getSessionFactory();

        // Создаём тестового клиента
        Client client = new Client("TEST123", "TestName", "TestSurname");

        // Сохраняем
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        }

        // Загружаем
        try (Session session = factory.openSession()) {
            Client loaded = session.find(Client.class, "TEST123");
            Assertions.assertNotNull(loaded, "Client must be loaded from DB");
            Assertions.assertEquals("TestName", loaded.getName());
        }
    }
}
