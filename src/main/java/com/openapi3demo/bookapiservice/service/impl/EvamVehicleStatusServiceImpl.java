package com.openapi3demo.bookapiservice.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openapi3demo.bookapiservice.classes.LocalDateTimeDeserializer;
import com.openapi3demo.bookapiservice.dto.EvamVehicleStatusRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;
import com.openapi3demo.bookapiservice.repository.EvamVehicleStatusRepository;
import com.openapi3demo.bookapiservice.service.EvamVehicleStatusService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EvamVehicleStatusServiceImpl implements EvamVehicleStatusService {

    private final EvamVehicleStatusRepository evamVehicleStatusRepository;
    private final Gson gson;

    public EvamVehicleStatusServiceImpl(EvamVehicleStatusRepository evamVehicleStatusRepository) {
        this.evamVehicleStatusRepository = evamVehicleStatusRepository;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    @Override
    public VehicleStatus[] updateVehicleStatus(EvamVehicleStatusRequestDTO evamVehicleStatusRequestDTO) {

        VehicleStatus[] vehicleStatuses = gson.fromJson(evamVehicleStatusRequestDTO.getVehicleStatus(),
                VehicleStatus[].class);

        for (int i = 0; i < vehicleStatuses.length; i++) {
            VehicleStatus vehicleStatus = vehicleStatuses[i];
            String id = String.valueOf(i + 1);

            Optional<VehicleStatus> existingVehicleStatus = evamVehicleStatusRepository.findById(id);

            VehicleStatus statusToUpdate;

            if (existingVehicleStatus.isPresent()) {
                statusToUpdate = existingVehicleStatus.get();
                statusToUpdate.setCategoryName(vehicleStatus.getCategoryName());
                statusToUpdate.setCategoryType(vehicleStatus.getCategoryType());
                statusToUpdate.setEvent(vehicleStatus.getEvent());
                statusToUpdate.setIsEndStatus(vehicleStatus.getIsEndStatus());
                statusToUpdate.setIsStartStatus(vehicleStatus.getIsStartStatus());
                statusToUpdate.setName(vehicleStatus.getName());
                statusToUpdate.setSuccessorName(vehicleStatus.getSuccessorName());
            } else {
                vehicleStatus.setId(id);
                statusToUpdate = vehicleStatus;
            }

            evamVehicleStatusRepository.save(statusToUpdate);
        }

        return vehicleStatuses;
    }

    @Override
    public VehicleStatus getById(String id) {
        return evamVehicleStatusRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No VehicleStatus found for id: %s".formatted(id)));
    }

    @Override
    public List<VehicleStatus> getAll() {
        return evamVehicleStatusRepository.findAll();
    }

    @Override
    public VehicleStatus getByName(String name) {
        return evamVehicleStatusRepository.findByName(name);
    }
}