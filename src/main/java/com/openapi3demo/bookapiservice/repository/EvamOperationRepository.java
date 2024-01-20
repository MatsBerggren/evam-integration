package com.openapi3demo.bookapiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.evam.Operation;

@Repository
public interface EvamOperationRepository extends MongoRepository<Operation, String> {
}