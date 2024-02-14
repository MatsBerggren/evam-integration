package com.dedalus.amphi_integration.service;

import com.dedalus.amphi_integration.dto.EvamRakelStateRequestDTO;
import com.dedalus.amphi_integration.model.evam.RakelState;

public interface EvamRakelStateService {
    RakelState updateRakelState(EvamRakelStateRequestDTO evamRakelStateRequestDTO);
    RakelState getById(String id);
}
