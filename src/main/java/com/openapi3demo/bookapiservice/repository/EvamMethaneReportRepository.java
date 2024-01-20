package com.openapi3demo.bookapiservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.amphi.MethaneReport;

@Repository
public interface EvamMethaneReportRepository extends MongoRepository<MethaneReport, String> {
}