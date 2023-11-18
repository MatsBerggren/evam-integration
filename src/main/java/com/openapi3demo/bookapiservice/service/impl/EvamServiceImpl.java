package com.openapi3demo.bookapiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.openapi3demo.bookapiservice.dto.EvamOperationRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.repository.EvamRepository;
import com.openapi3demo.bookapiservice.service.EvamService;

@Service
public class EvamServiceImpl implements EvamService {

    @Autowired
    EvamRepository evamRepository;

    @Override
    public Operation createNew(EvamOperationRequestDTO evamOperationRequestDTO) {
        
        Operation operation = new Gson().fromJson(evamOperationRequestDTO.getJson(), Operation.class);

        return evamRepository.save(operation);
    }

    @Override
    public Operation getByOperationId(String operationID) {
        return evamRepository.findByOperationID(operationID);
    }
}
