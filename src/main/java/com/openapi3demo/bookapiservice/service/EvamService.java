package com.openapi3demo.bookapiservice.service;

import com.openapi3demo.bookapiservice.dto.EvamOperationRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.Operation;

public interface EvamService {

    Operation createNew(EvamOperationRequestDTO evamOperationRequestDTO);

    Operation getByOperationId(String operationID);

}
