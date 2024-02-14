package com.dedalus.amphi_integration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dedalus.amphi_integration.model.evam.Operation;

@Repository
public interface EvamOperationRepository extends MongoRepository<Operation, String> {
}
