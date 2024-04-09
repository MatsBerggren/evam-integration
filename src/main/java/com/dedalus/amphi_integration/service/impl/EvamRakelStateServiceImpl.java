package com.dedalus.amphi_integration.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.dedalus.amphi_integration.dto.EvamRakelStateRequestDTO;
import com.dedalus.amphi_integration.model.evam.RakelState;
import com.dedalus.amphi_integration.repository.EvamRakelStateRepository;
import com.dedalus.amphi_integration.service.EvamRakelStateService;

@Service
public class EvamRakelStateServiceImpl implements EvamRakelStateService {

    @Autowired
    EvamRakelStateRepository evamRakelStateRepository;

    @Override
    public RakelState updateRakelState(EvamRakelStateRequestDTO evamRakelStateRequestDTO) {
        RakelState rakelState = new Gson().fromJson(evamRakelStateRequestDTO.getRakelState(), RakelState.class);
        
        Optional<RakelState> existingRakelState = evamRakelStateRepository.findById("1");
        if(existingRakelState.isEmpty()) {
            rakelState.setId("1");
            rakelState.setUnitId("1");
            return evamRakelStateRepository.save(rakelState);
        } else {
            if(rakelState.getMsisdn().equals("0000567")) {
                existingRakelState.get().setMsisdn("3393090");    
            } else {
                existingRakelState.get().setMsisdn(rakelState.getMsisdn());
            }
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
