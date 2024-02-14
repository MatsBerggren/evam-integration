package com.dedalus.amphi_integration.service;

import com.dedalus.amphi_integration.dto.EvamOperationRequestDTO;
import com.dedalus.amphi_integration.model.evam.Operation;

public interface EvamOperationService {
    Operation updateOperation(EvamOperationRequestDTO evamOperationRequestDTO);
    Operation getById(String id);
}
