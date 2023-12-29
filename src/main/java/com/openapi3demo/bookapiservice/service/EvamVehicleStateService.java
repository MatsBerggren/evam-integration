package com.openapi3demo.bookapiservice.service;

import com.openapi3demo.bookapiservice.dto.EvamVehicleStateRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.VehicleState;

public interface EvamVehicleStateService {
    VehicleState updateVehicleState(EvamVehicleStateRequestDTO evamVehicleStateRequestDTO);

    VehicleState getById(String id);
}
