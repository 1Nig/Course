package com.radosti.app.service;

import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;
import com.radosti.app.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.radosti.app.dto.ApartmentCreateRequest;
import com.radosti.app.repository.ApartmentRepository;


@Service
public class ApartmentService {
    private final ClientRepository clientRepository;
    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository, ClientRepository clientRepository) {
        this.apartmentRepository = apartmentRepository;
        this.clientRepository = clientRepository;
    }

    public Apartment registerApartment(ApartmentCreateRequest request){
        Apartment apartment = new Apartment(request.id(), request.price());
        return apartmentRepository.save(apartment);
    }
    public Apartment findById(int id) {
        return apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
    }

    public Apartment reserveApartment(int apartmentId, String passportID){
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        Client  client = clientRepository.findById(passportID)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (apartment.isReserved()){
            throw new RuntimeException("Apartment is already reserved.");
        }

        apartment.setReserved(true);
        apartment.setClient(client);

        return apartmentRepository.save(apartment);
    }
    public Apartment releaseApartment(int apartmentId){
        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        if(!apartment.isReserved()){
            throw new RuntimeException("The apartment wasn't reserved.");
        }

        apartment.setReserved(false);
        apartment.setClient(null);

        return apartmentRepository.save(apartment);
    }

    public Page<Apartment> listApartments(Pageable pageable) {

        return apartmentRepository.findAll(pageable);
    }



}
