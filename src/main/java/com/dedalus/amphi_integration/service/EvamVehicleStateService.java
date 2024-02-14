package com.dedalus.amphi_integration.service;

import com.dedalus.amphi_integration.dto.EvamVehicleStateRequestDTO;
import com.dedalus.amphi_integration.model.evam.VehicleState;

public interface EvamVehicleStateService {
    VehicleState updateVehicleState(EvamVehicleStateRequestDTO evamVehicleStateRequestDTO);

    VehicleState getById(String id);
}
