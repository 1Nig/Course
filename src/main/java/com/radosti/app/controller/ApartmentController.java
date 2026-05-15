package com.radosti.app.controller;

import com.radosti.app.domain.Apartment;
import com.radosti.app.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping("/register")
    public String registerApartment(@RequestParam int id, @RequestParam double price) {
        apartmentService.registerApartment(id, price);
        return "Apartment registered successfully";
    }

    @GetMapping("/{id}")
    public Apartment getApartment(@PathVariable int id) {
        return apartmentService.findById(id);
    }

    @PostMapping("/{id}/reserve")
    public String reserveApartment(@PathVariable int id, @RequestParam String passportID) {
        apartmentService.reserveApartment(id, passportID);
        return "Reservation attempt completed";
    }

    @PostMapping("/{id}/release")
    public String releaseApartment(@PathVariable int id) {
        apartmentService.releaseApartment(id);
        return "Release attempt completed";
    }

    @GetMapping
    public List<Apartment> listApartments(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        return apartmentService.listApartments(page, size, sortBy);
    }
}
