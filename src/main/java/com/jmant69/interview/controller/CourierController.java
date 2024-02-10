package com.jmant69.interview.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmant69.interview.model.Courier;
import com.jmant69.interview.service.CourierService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourierController {

    private CourierService courierService;

    @GetMapping("/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {

        return ResponseEntity.ok(courierService.getAllCouriers());
    }
    
	@PutMapping("/updatecourier/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Courier courier) {
		try {
			Courier updatedCourier = courierService.update(courier, id);

			return ResponseEntity.ok(updatedCourier);
			
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
