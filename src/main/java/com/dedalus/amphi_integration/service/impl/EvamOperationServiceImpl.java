package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamOperationRequestDTO;
import com.dedalus.amphi_integration.model.evam.Operation;
import com.dedalus.amphi_integration.model.evam.VehicleState;
import com.dedalus.amphi_integration.repository.*;
import com.dedalus.amphi_integration.service.EvamOperationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class EvamOperationServiceImpl implements EvamOperationService {

    @Autowired
    private EvamOperationRepository evamOperationRepository;
    @Autowired
    private EvamVehicleStateRepository evamVehicleStateRepository;
    @Autowired
    private AmphiStateEntryRepository amphiStateEntryRepository;
    @Autowired
    private EvamMethaneReportRepository evamMethaneRepository;
    @Autowired
    private EvamTripLocationHistoryRepository evamTripLocationHistoryRepository;
    @Autowired
    private EvamVehicleStatusRepository evamVehicleStatusRepository;
    @Autowired
    private AmphiDestinationRepository amphiDestinationRepository;

    private Gson gson;

    public EvamOperationServiceImpl() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();
    }

    @Override
    public Operation updateOperation(EvamOperationRequestDTO evamOperationRequestDTO) {

        Operation operation = gson.fromJson(evamOperationRequestDTO.getOperation(), Operation.class);
        Optional<Operation> existingOperation = evamOperationRepository.findById("1");

        if (existingOperation.isEmpty() || !operation.getFullId().equals(existingOperation.get().getFullId())) {
            cleanDB();
            operation = saveNewOperation(operation);
        }

        return updateExistingOperation(existingOperation.get(), operation);
    }

    private void cleanDB() {
        evamMethaneRepository.deleteAll();
        evamOperationRepository.deleteAll();
        amphiStateEntryRepository.deleteAll();
        evamTripLocationHistoryRepository.deleteAll();
        evamVehicleStateRepository.save(new VehicleState("1"));
    }

    private Operation saveNewOperation(Operation operation) {
        operation.setId("1");
        operation.setAmPHIUniqueId(UUID.randomUUID().toString());
        return evamOperationRepository.save(operation);
    }

    private Operation updateExistingOperation(Operation existingOperation, Operation operation) {
        existingOperation.updateFrom(operation);
        return evamOperationRepository.save(existingOperation);
    }

    @Override
    public Operation getById(String id) {
        return evamOperationRepository.findById(id).orElseThrow(() -> new RuntimeException("No Operation found for id: %s".formatted(id)));
    }

    public String dateFixShort(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Stockholm"));
        return zonedDateTime.format(dateTimeFormatter);
    }
}