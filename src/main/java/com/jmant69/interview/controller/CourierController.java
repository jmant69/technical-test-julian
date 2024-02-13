package com.jmant69.interview.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmant69.interview.exception.CourierNotFoundException;
import com.jmant69.interview.model.Courier;
import com.jmant69.interview.model.CourierPut;
import com.jmant69.interview.service.CourierService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourierController {

	private CourierService courierService;

	@GetMapping("/couriers")
	public ResponseEntity<List<Courier>> getAllCouriers(@RequestParam(defaultValue = "false") Boolean isActive) {
		if (isActive) {
			return ResponseEntity.ok(courierService.getAllActiveCouriers(isActive));
		} else {
			return ResponseEntity.ok(courierService.getAllCouriers());
		}
	}

	@PutMapping("/updatecourier/{id}")
	public ResponseEntity<Courier> update(@PathVariable("id") Long id, @RequestBody @Valid CourierPut courierPut)
			throws CourierNotFoundException {
//		try {
//			Courier updatedCourier = courierService.update(courierPut, id);
//
//			return ResponseEntity.ok(updatedCourier);
//
//		} catch (NotFoundException e) {
//			throw new ResourceN
//			return ResponseEntity.notFound().build();
//		}
//		Courier updatedCourier = courierService.update(courierPut, id);
//
//		return ResponseEntity.ok(updatedCourier);
		Courier updatedCourier = courierService.update(courierPut, id);

		return ResponseEntity.ok(updatedCourier);
	}

}
