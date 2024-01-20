package com.openapi3demo.bookapiservice.service;

import java.util.List;

import com.openapi3demo.bookapiservice.dto.EvamMethaneReportRequestDTO;
import com.openapi3demo.bookapiservice.model.amphi.MethaneReport;

public interface EvamMethaneReportService {
    MethaneReport updateMethaneReport(EvamMethaneReportRequestDTO evamMethaneReportRequestDTO);

    List<MethaneReport> getAll();

    MethaneReport getById(String id);
}
