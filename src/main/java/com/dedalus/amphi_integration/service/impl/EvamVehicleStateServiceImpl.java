package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamVehicleStateRequestDTO;
import com.dedalus.amphi_integration.model.amphi.StateEntry;
import com.dedalus.amphi_integration.model.evam.VehicleState;
import com.dedalus.amphi_integration.repository.AmphiStateEntryRepository;
import com.dedalus.amphi_integration.repository.EvamVehicleStateRepository;
import com.dedalus.amphi_integration.repository.EvamVehicleStatusRepository;
import com.dedalus.amphi_integration.service.AmphiStateEntryService;
import com.dedalus.amphi_integration.service.EvamVehicleStateService;

@Service
public class EvamVehicleStateServiceImpl implements EvamVehicleStateService {

    @Autowired
    EvamVehicleStateRepository evamVehicleStateRepository;
    @Autowired
    EvamVehicleStatusRepository evamVehicleStatusRepository;
    @Autowired
    AmphiStateEntryService amphiStateEntryService;
    @Autowired
    AmphiStateEntryRepository amphiStateEntryRepository;

    @Override
    public VehicleState updateVehicleState(EvamVehicleStateRequestDTO evamVehicleStateRequestDTO) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();

        VehicleState vehicleState = gson.fromJson(evamVehicleStateRequestDTO.getVehicleState(),
                VehicleState.class);

        Integer previousstate;
        Optional<StateEntry> stateEntries = amphiStateEntryRepository.findAll(Sort.by(Sort.Direction.DESC, "time"))
                .stream().findFirst();
        if (stateEntries.isEmpty()) {
            previousstate = 0;
        } else {
            previousstate = stateEntries.get().getTo_id();
        }

        Optional<VehicleState> existingVehicleState = evamVehicleStateRepository
                .findById("1");
        if (existingVehicleState.isEmpty()) {
            vehicleState.setId("1");

            String vehicleStatusName = vehicleState.getVehicleStatus().getName();
            if (!previousstate.equals(
                    
                    Integer.parseInt(
                        evamVehicleStatusRepository.findByName(vehicleStatusName).getId()
                    )
                    )
                ) {
                StateEntry stateEntry = StateEntry.builder()
                        .from_id(0)
                        .to_id(Integer.parseInt(evamVehicleStatusRepository
                                .findByName(vehicleState.getVehicleStatus().getName()).getId()))
                        .distance(0)
                        .time(dateFix(LocalDateTime.now()))
                        .build();
                amphiStateEntryService.updateStateEntry(stateEntry);
            }
            return evamVehicleStateRepository.save(vehicleState);

        } else {
            existingVehicleState.get().setActiveCaseFullId(vehicleState.activeCaseFullId);
            existingVehicleState.get().setTimestamp(vehicleState.timestamp);
            existingVehicleState.get().setVehicleStatus(vehicleState.vehicleStatus);
            existingVehicleState.get().setVehicleLocation(vehicleState.vehicleLocation);

            if (!previousstate.equals(Integer.parseInt(
                    evamVehicleStatusRepository.findByName(vehicleState.getVehicleStatus().getName()).getId()))) {
                StateEntry stateEntry = StateEntry.builder()
                        .from_id(previousstate)
                        .to_id(Integer.parseInt(evamVehicleStatusRepository
                                .findByName(vehicleState.getVehicleStatus().getName()).getId()))
                        .distance(0)
                        .time(dateFix(LocalDateTime.now()))
                        .build();
                amphiStateEntryService.updateStateEntry(stateEntry);
            }
            return evamVehicleStateRepository.save(existingVehicleState.get());

        }
    }

    @Override
    public VehicleState getById(String id) {
        return evamVehicleStateRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No VehicleState found for id: %s".formatted(id)));
    }

    private String dateFix(LocalDateTime localDateTime) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        String returnDate = zonedDateTime.format(dateTimeFormatter);

        return returnDate;
    }
}