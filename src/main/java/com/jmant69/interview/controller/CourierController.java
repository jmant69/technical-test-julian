package com.jmant69.interview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;

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
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid CourierPut courierPut) {
		try {
			Courier updatedCourier = courierService.update(courierPut, id);

			return ResponseEntity.ok(updatedCourier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
	    String name = ex.getName();
	    String type = ex.getRequiredType().getSimpleName();
	    Object value = ex.getValue();
	    String message = String.format("'%s' should be a valid '%s' and '%s' isn't", 
	                                   name, type, value);

	    System.out.println(message);
	    return message;
	}

}
