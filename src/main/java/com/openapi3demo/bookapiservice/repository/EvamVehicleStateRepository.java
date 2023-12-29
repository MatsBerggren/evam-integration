package com.openapi3demo.bookapiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.evam.VehicleState;

@Repository
public interface EvamVehicleStateRepository extends MongoRepository<VehicleState, String> {
}