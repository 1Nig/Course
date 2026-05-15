package com.radosti.app.dao;

import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApartmentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save (Apartment apartment) {
        entityManager.persist(apartment);
    }
    @Transactional(readOnly = true)
    public Apartment findById(int id){
        return entityManager.find(Apartment.class, id);
    }
    @Transactional
    public void updateReservation(int id, String client_passport, boolean isReserved) {
            Apartment apartment = entityManager.find(Apartment.class, id);
            Client client = entityManager.find(Client.class,client_passport);
                if(apartment== null){
                    System.out.println("The apartment wasn't found.");
                }
                else if(client == null){
                    System.out.println("The client wasn't found.");
                }
                else{
                apartment.setReserved(isReserved);
                apartment.setClient(client);
                }
    }
    @Transactional
    public void updateRelease(int id) {
            Apartment apartment = entityManager.find(Apartment.class, id);
            if(apartment == null){
                System.out.println("The apartment wasn't found.");
            }
            else{
                apartment.setReserved(false);
                apartment.setClient(null);
            }
    }
    @Transactional(readOnly = true)
    public List<Apartment> findAll(int page, int size, String sortBy) {
        return entityManager.createQuery(
                            "from Apartment order by " + sortBy, Apartment.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

