package com.dedalus.amphi_integration.repository;

import java.time.LocalDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dedalus.amphi_integration.model.amphi.Assignment;

@Repository
public interface AmphiAssignmentRepository extends MongoRepository<Assignment, String> {
    void deleteByCreatedBefore(LocalDate date);
}