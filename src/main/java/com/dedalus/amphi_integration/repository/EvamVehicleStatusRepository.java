package com.dedalus.amphi_integration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dedalus.amphi_integration.model.evam.VehicleStatus;

@Repository
public interface EvamVehicleStatusRepository extends MongoRepository<VehicleStatus, String> {
    VehicleStatus findByName(String name);
}