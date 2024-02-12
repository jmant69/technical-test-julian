package com.jmant69.interview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.jmant69.interview.model.Courier;
import com.jmant69.interview.model.CourierPut;
import com.jmant69.interview.repository.CourierEntity;
import com.jmant69.interview.repository.CourierRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourierService {

	private CourierTransformer courierTransformer;
	private CourierRepository repository;

	public List<Courier> getAllCouriers() {
		return repository.findAll().stream().map(courierTransformer::toCourier).collect(Collectors.toList());
	}
	
	public List<Courier> getAllActiveCouriers(Boolean isActive) {
		return repository.findByActive(isActive).stream().map(courierTransformer::toCourier).collect(Collectors.toList());
	}

	public Courier update(CourierPut courier, Long id) throws NotFoundException {
		if (!repository.existsById(id)) {
			throw new NotFoundException();
		}
		CourierEntity courierEntity = courierTransformer.toCourierEntity(courier, id);
		CourierEntity savedCourierEntity = repository.save(courierEntity);
		return courierTransformer.toCourier(savedCourierEntity);
	}
}
