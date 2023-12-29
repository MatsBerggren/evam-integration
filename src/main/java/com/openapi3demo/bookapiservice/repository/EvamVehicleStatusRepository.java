package com.openapi3demo.bookapiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;

@Repository
public interface EvamVehicleStatusRepository extends MongoRepository<VehicleStatus, String> {
    VehicleStatus findByName(String name);
}