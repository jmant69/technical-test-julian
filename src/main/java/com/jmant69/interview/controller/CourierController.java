package com.jmant69.interview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmant69.interview.model.Courier;
import com.jmant69.interview.service.CourierService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourierController {

    private CourierService courierService;

    @GetMapping("/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {

        return ResponseEntity.ok(courierService.getAllCouriers());
    }

}
