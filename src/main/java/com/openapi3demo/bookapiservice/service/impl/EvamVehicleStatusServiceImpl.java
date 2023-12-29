package com.openapi3demo.bookapiservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openapi3demo.bookapiservice.classes.LocalDateTimeDeserializer;
import com.openapi3demo.bookapiservice.dto.EvamVehicleStatusRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;
import com.openapi3demo.bookapiservice.repository.EvamVehicleStatusRepository;
import com.openapi3demo.bookapiservice.service.EvamVehicleStatusService;

@Service
public class EvamVehicleStatusServiceImpl implements EvamVehicleStatusService {

    @Autowired
    EvamVehicleStatusRepository evamVehicleStatusRepository;

    @Override
    public VehicleStatus[] updateVehicleStatus(EvamVehicleStatusRequestDTO evamVehicleStatusRequestDTO) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();

        VehicleStatus[] vehicleStatuses = gson.fromJson(evamVehicleStatusRequestDTO.getVehicleStatus(),
                VehicleStatus[].class);

        Integer index = 0;
        for (VehicleStatus vehicleStatus : vehicleStatuses) {
            Integer finalIndex = index+1;
            Optional<VehicleStatus> existingVehicleStatus = evamVehicleStatusRepository.findById(finalIndex.toString());

            if (!existingVehicleStatus.isPresent()) {
                vehicleStatus.setId(finalIndex.toString());
                evamVehicleStatusRepository.save(vehicleStatus);
            } else {
                existingVehicleStatus.get().setCategoryName(vehicleStatus.getCategoryName());
                existingVehicleStatus.get().setCategoryType(vehicleStatus.getCategoryType());
                existingVehicleStatus.get().setEvent(vehicleStatus.getEvent());
                existingVehicleStatus.get().setIsEndStatus(vehicleStatus.getIsEndStatus());
                existingVehicleStatus.get().setIsStartStatus(vehicleStatus.getIsStartStatus());
                existingVehicleStatus.get().setName(vehicleStatus.getName());
                existingVehicleStatus.get().setSuccessorName(vehicleStatus.getSuccessorName());
                evamVehicleStatusRepository.save(existingVehicleStatus.get());
            }
            index++;
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
