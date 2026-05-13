package com.radosti.app.integration;

import com.radosti.app.command.ApartmentRegister;
import com.radosti.app.command.ApartmentReserve;
import com.radosti.app.command.ClientRegister;
import com.radosti.app.config.Hibernate;
import com.radosti.app.dao.ApartmentDAO;
import com.radosti.app.dao.ClientDAO;
import com.radosti.app.domain.Apartment;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class integrationTest {

    private static SessionFactory factory;
    private ApartmentService apartmentService;
    private ClientService clientService;

    @BeforeAll
    static void initFactory() {
        factory = Hibernate.getSessionFactory();
    }

    @BeforeEach
    void setup() {
        ClientDAO clientDAO = new ClientDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO();

        clientService = new ClientService(clientDAO);
        apartmentService = new ApartmentService(apartmentDAO, clientService);

        // Очищаем таблицы перед каждым тестом
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("delete from Apartment").executeUpdate();
            session.createMutationQuery("delete from Client").executeUpdate();
            tx.commit();
        }
    }

    @Test
    void Test_ApartmentRegistration_And_MakingList() {
        ApartmentRegister apRegister = new ApartmentRegister(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};
        apRegister.execute(ap_register);

        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1, list.size());
        assertEquals(100.0, list.get(0).getPrice());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve() {
        ApartmentRegister apRegister = new ApartmentRegister(apartmentService);
        ClientRegister clientRegister = new ClientRegister(clientService);
        ApartmentReserve apReserve = new ApartmentReserve(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};
        String[] client_register = {"client", "register", "AB123", "John", "Johnson"};
        String[] ap_reserve = {"apartment", "reserve", "1", "AB123"};

        apRegister.execute(ap_register);
        clientRegister.execute(client_register);
        apReserve.execute(ap_reserve);

        Apartment a = apartmentService.findById(1);
        assertTrue(a.isReserved());
        assertEquals("AB123", a.getClient().getPassportID());
    }

    @Test
    void Test_ApartmentRegistration_ClientRegistration_ApartmentReserve_MakingList() {
        ApartmentRegister apRegister = new ApartmentRegister(apartmentService);
        ClientRegister clientRegister = new ClientRegister(clientService);
        ApartmentReserve apReserve = new ApartmentReserve(apartmentService);

        String[] ap_register = {"apartment", "register", "1", "100.0"};
        String[] client_register = {"client", "register", "AB123", "John", "Johnson"};
        String[] ap_reserve = {"apartment", "reserve", "1", "AB123"};

        apRegister.execute(ap_register);
        clientRegister.execute(client_register);
        apReserve.execute(ap_reserve);

        List<Apartment> list = apartmentService.listApartments(1, 1, "price");

        assertEquals(1, list.size());
        assertTrue(list.get(0).isReserved());
        assertEquals("Johnson", list.get(0).getClient().getSurname());
    }
}
