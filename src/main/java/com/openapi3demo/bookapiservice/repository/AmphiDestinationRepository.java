package com.openapi3demo.bookapiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.amphi.Destination;

@Repository
public interface AmphiDestinationRepository extends MongoRepository<Destination, String> {
    Destination findByNameAndType(String name, String type);
}