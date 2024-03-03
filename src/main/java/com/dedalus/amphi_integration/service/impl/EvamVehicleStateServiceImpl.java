package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dedalus.amphi_integration.classes.DateFix;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamVehicleStateRequestDTO;
import com.dedalus.amphi_integration.model.amphi.StateEntry;
import com.dedalus.amphi_integration.model.evam.Operation;
import com.dedalus.amphi_integration.model.evam.OperationState;
import com.dedalus.amphi_integration.model.evam.VehicleState;
import com.dedalus.amphi_integration.model.evam.VehicleStatus;
import com.dedalus.amphi_integration.repository.AmphiStateEntryRepository;
import com.dedalus.amphi_integration.repository.EvamOperationRepository;
import com.dedalus.amphi_integration.repository.EvamVehicleStateRepository;
import com.dedalus.amphi_integration.repository.EvamVehicleStatusRepository;
import com.dedalus.amphi_integration.service.AmphiStateEntryService;
import com.dedalus.amphi_integration.service.EvamVehicleStateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    @Autowired
    EvamOperationRepository evamOperationRepository;

    @Override
    public VehicleState updateVehicleState(EvamVehicleStateRequestDTO evamVehicleStateRequestDTO) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();

        VehicleState vehicleState = gson.fromJson(evamVehicleStateRequestDTO.getVehicleState(),VehicleState.class);

        Integer previousstate;
        Optional<StateEntry> stateEntries = amphiStateEntryRepository.findAll(Sort.by(Sort.Direction.DESC, "time"))
                .stream().findFirst();
        if (stateEntries.isEmpty()) {
            previousstate = 0;
        } else {
            previousstate = stateEntries.get().getTo_id();
        }

        evamVehicleStateRepository.deleteById("1");;
        vehicleState.setId("1");

        String vehicleStatusName = vehicleState.getVehicleStatus().getName();
        Integer vehicleStatusId;
        try {
            vehicleStatusId = Integer.parseInt(evamVehicleStatusRepository.findByName(vehicleStatusName).getId());
        }
        catch (Exception e) {
            vehicleStatusId = 0;
        }
        if (!previousstate.equals(vehicleStatusId)) {
            StateEntry stateEntry = StateEntry.builder()
                    .from_id(0)
                    .to_id(Integer.parseInt(evamVehicleStatusRepository.findByName(Optional.ofNullable(vehicleState)
                                                                                                .map(VehicleState::getVehicleStatus)
                                                                                                .map(VehicleStatus::getName)
                                                                                                .orElse("")).getId()))
                    .distance(0)
                    .time(DateFix.dateFix(LocalDateTime.now()))
                    .build();
            amphiStateEntryService.updateStateEntry(stateEntry);
            if(stateEntry.getTo_id() >= 5) {
                Optional<Operation> existingOperation = evamOperationRepository.findById("1");
                if (!existingOperation.isEmpty()) {
                    existingOperation.get().setOperationState(OperationState.AVAILABLE);
                    evamOperationRepository.save(existingOperation.get());
                }
            } else {
                Optional<Operation> existingOperation = evamOperationRepository.findById("1");
                if (!existingOperation.isEmpty()) {
                    existingOperation.get().setOperationState(OperationState.ACTIVE);
                    evamOperationRepository.save(existingOperation.get());
                }
            }
        }
        return evamVehicleStateRepository.save(vehicleState);
    }

    @Override
    public VehicleState getById(String id) {
        return evamVehicleStateRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No VehicleState found for id: %s".formatted(id)));
    }

}
