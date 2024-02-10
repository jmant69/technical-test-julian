package com.jmant69.interview.service;

import org.springframework.stereotype.Component;

import com.jmant69.interview.model.Courier;
import com.jmant69.interview.repository.CourierEntity;

@Component
public class CourierTransformer {

    public Courier toCourier(CourierEntity entity) {
        return Courier.builder()
                .id(entity.getId())
                .name(String.format("%s %s", entity.getFirstName(), entity.getLastName()))
                .active(entity.isActive())
                .build();
    }
    
    public CourierEntity toCourierEntity(Courier courier) {
    	String courierName = courier.getName();
    	String[] nameArray = courierName.split(" ");
    	return CourierEntity.builder()
        		.id(courier.getId())
        		.firstName(nameArray[0])
        		.lastName(nameArray[1])
        		.active(courier.isActive())
                .build();
    }

}
