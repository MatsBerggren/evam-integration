package com.openapi3demo.bookapiservice.service;

import java.util.List;

import com.openapi3demo.bookapiservice.dto.EvamVehicleStatusRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;

public interface EvamVehicleStatusService {
    VehicleStatus[] updateVehicleStatus(EvamVehicleStatusRequestDTO evamVehicleStatusRequestDTO);

    List<VehicleStatus> getAll();

    VehicleStatus getByName(String name);

    VehicleStatus getById(String id);
}
