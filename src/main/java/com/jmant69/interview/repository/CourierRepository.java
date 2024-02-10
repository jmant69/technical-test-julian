package com.jmant69.interview.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

	Collection<CourierEntity> findByActive(Boolean active);

}
