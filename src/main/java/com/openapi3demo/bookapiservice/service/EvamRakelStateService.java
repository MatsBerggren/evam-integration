package com.openapi3demo.bookapiservice.service;

import com.openapi3demo.bookapiservice.dto.EvamRakelStateRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.RakelState;

public interface EvamRakelStateService {
    RakelState updateRakelState(EvamRakelStateRequestDTO evamRakelStateRequestDTO);
    RakelState getById(String id);
}
