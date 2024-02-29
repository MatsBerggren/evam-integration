package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamVehicleStatusRequestDTO;
import com.dedalus.amphi_integration.model.evam.VehicleStatus;
import com.dedalus.amphi_integration.repository.EvamVehicleStatusRepository;
import com.dedalus.amphi_integration.service.EvamVehicleStatusService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        VehicleStatus[] vehicleStatuses = gson.fromJson(evamVehicleStatusRequestDTO.getVehicleStatus(),VehicleStatus[].class);
        evamVehicleStatusRepository.deleteAll();

        for (int i = 0; i < vehicleStatuses.length; i++) {
            VehicleStatus vehicleStatus = vehicleStatuses[i];
            String id = String.valueOf(i + 1);

            vehicleStatus.setId(id);
            evamVehicleStatusRepository.save(vehicleStatus);
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