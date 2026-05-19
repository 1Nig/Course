package com.radosti.app.controller;

import com.radosti.app.domain.Apartment;
import com.radosti.app.dto.ApartmentCreateRequest;
import com.radosti.app.service.ApartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerApartment(@RequestBody ApartmentCreateRequest request) {
        Apartment created = apartmentService.registerApartment(request);
        return ResponseEntity
                .status(201)
                .body(Map.of("id", created.getId()));
    }

    @GetMapping("/{id}")
    public Apartment getApartment(@PathVariable int id) {
        return apartmentService.findById(id);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<Map<String, Object>> reserveApartment(
            @PathVariable int id,
            @RequestBody Map<String, String> request
    ) {
        String passportID = request.get("passportID");

        Apartment updated = apartmentService.reserveApartment(id, passportID);

        return ResponseEntity
                .ok(Map.of("id", updated.getId(), "status", "reserved"));
    }


    @PostMapping("/{id}/release")
    public ResponseEntity<Map<String, Object>> releaseApartment(
            @PathVariable int id) {
        Apartment updated = apartmentService.releaseApartment(id);
        return ResponseEntity
                .ok(Map.of("id", updated.getId(), "status", "free"));
    }

    @GetMapping
    public ResponseEntity<Page<Apartment>> listApartments(Pageable pageable) {
        return ResponseEntity.ok(apartmentService.listApartments(pageable));
    }

}
