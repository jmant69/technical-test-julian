package com.jmant69.interview.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.jmant69.interview.model.Courier;
import com.jmant69.interview.repository.CourierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourierService {

    private CourierTransformer courierTransformer;
    private CourierRepository repository;

    public List<Courier> getAllCouriers() {
        return repository.findAll()
                .stream()
                .map(courierTransformer::toCourier)
                .collect(Collectors.toList());
    }
}
