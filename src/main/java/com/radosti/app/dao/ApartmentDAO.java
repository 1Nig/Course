package com.radosti.app.dao;

import com.radosti.app.db.DatabaseConnection;
import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.radosti.app.config.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ApartmentDAO {
    public void save (Apartment apartment) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(apartment);
            tx.commit();

        }
    }
    public Apartment findById(int id){

            try (Session session = Hibernate.getSessionFactory().openSession()) {
                return session.find(Apartment.class, id);
            }

    }
    public void updateReservation(int id, String client_passport, boolean isReserved) {

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Apartment apartment = session.find(Apartment.class, id);
            if(apartment == null){
                System.out.println("The apartment wasn't found.");
            }
            else{
                Client client = session.find(Client.class,client_passport);
                if(client == null){
                    System.out.println("The client wasn't found.");
                }
                else{
                apartment.setReserved(isReserved);
                apartment.setClient(client);}
            }
            tx.commit();
        }
    }
    public void updateRelease(int id, boolean isReserved) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Apartment apartment = session.find(Apartment.class, id);
            if(apartment == null){
                System.out.println("The apartment wasn't found.");
            }
            else{
                apartment.setReserved(false);
                apartment.setClient(null);
            }
            tx.commit();
        }
    }
    public List<Apartment> findAll(int page, int size, String sortBy) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {

            return session.createQuery(
                            "from Apartment order by " + sortBy, Apartment.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
        }
    }



}
