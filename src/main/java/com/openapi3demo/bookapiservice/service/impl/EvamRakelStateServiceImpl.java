package com.openapi3demo.bookapiservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.openapi3demo.bookapiservice.dto.EvamRakelStateRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.RakelState;
import com.openapi3demo.bookapiservice.repository.EvamRakelStateRepository;
import com.openapi3demo.bookapiservice.service.EvamRakelStateService;

@Service
public class EvamRakelStateServiceImpl implements EvamRakelStateService {

    @Autowired
    EvamRakelStateRepository evamRakelStateRepository;

    @Override
    public RakelState updateRakelState(EvamRakelStateRequestDTO evamRakelStateRequestDTO) {
        RakelState rakelState = new Gson().fromJson(evamRakelStateRequestDTO.getRakelState(), RakelState.class);
        
        Optional<RakelState> existingRakelState = evamRakelStateRepository.findById("1");
        if(!existingRakelState.isPresent()) {
            rakelState.setId("1");
            rakelState.setUnitId("1");
            return evamRakelStateRepository.save(rakelState);
        } else {
            existingRakelState.get().setMsisdn(rakelState.getMsisdn());
            existingRakelState.get().setIssi(rakelState.getIssi());
            existingRakelState.get().setGssi(rakelState.getGssi());
            return evamRakelStateRepository.save(existingRakelState.get());
        }
    }

    @Override
    public RakelState getById(String id) {
        return evamRakelStateRepository.findById(id).orElseThrow(
            () -> new RuntimeException("No RakelState found for id: %s".formatted(id)));
    }
}
