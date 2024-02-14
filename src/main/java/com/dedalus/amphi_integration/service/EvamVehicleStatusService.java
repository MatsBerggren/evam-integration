package com.dedalus.amphi_integration.service;

import java.util.List;

import com.dedalus.amphi_integration.dto.EvamVehicleStatusRequestDTO;
import com.dedalus.amphi_integration.model.evam.VehicleStatus;

public interface EvamVehicleStatusService {
    VehicleStatus[] updateVehicleStatus(EvamVehicleStatusRequestDTO evamVehicleStatusRequestDTO);

    List<VehicleStatus> getAll();

    VehicleStatus getByName(String name);

    VehicleStatus getById(String id);
}
