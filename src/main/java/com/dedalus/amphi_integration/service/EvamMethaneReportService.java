package com.dedalus.amphi_integration.service;

import java.util.List;

import com.dedalus.amphi_integration.dto.EvamMethaneReportRequestDTO;
import com.dedalus.amphi_integration.model.amphi.MethaneReport;

public interface EvamMethaneReportService {
    MethaneReport updateMethaneReport(EvamMethaneReportRequestDTO evamMethaneReportRequestDTO);

    List<MethaneReport> getAll();

    MethaneReport getById(String id);
}
