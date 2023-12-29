package com.openapi3demo.bookapiservice.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openapi3demo.bookapiservice.classes.LocalDateTimeDeserializer;
import com.openapi3demo.bookapiservice.dto.EvamOperationRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.repository.AmphiStateEntryRepository;
import com.openapi3demo.bookapiservice.repository.EvamOperationRepository;
import com.openapi3demo.bookapiservice.repository.EvamVehicleStateRepository;
import com.openapi3demo.bookapiservice.service.EvamOperationService;

@Service
public class EvamOperationServiceImpl implements EvamOperationService {

    @Autowired
    EvamOperationRepository evamOperationRepository;
    @Autowired
    EvamVehicleStateRepository evamVehicleStateRepository;
    @Autowired
    AmphiStateEntryRepository amphiStateEntryRepository;

    @Override
    public Operation updateOperation(EvamOperationRequestDTO evamOperationRequestDTO) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();

        Operation operation = gson.fromJson(evamOperationRequestDTO.getOperation(), Operation.class);
        Optional<Operation> existingOperation = evamOperationRepository.findById("1");

        String oldOperation = existingOperation.get().getOperationID();        
        String newOperation = operation.getOperationID();
        
        if (!oldOperation.equals(newOperation)) {
            amphiStateEntryRepository.deleteAll();
            evamVehicleStateRepository.deleteAll();
        }

        if (!existingOperation.isPresent()) {
            operation.setId("1");
            return evamOperationRepository.save(operation);
        } else {
            existingOperation.get().setOperationID(operation.getOperationID());
            existingOperation.get()
                    .setAdditionalCoordinationInformation(operation.getAdditionalCoordinationInformation());
            existingOperation.get().setAdditionalInfo(operation.getAdditionalInfo());
            existingOperation.get().setAlarmCategory(operation.getAlarmCategory());
            existingOperation.get().setAlarmEventCode(operation.getAlarmEventCode());
            existingOperation.get().setAlarmEventText(operation.getAlarmEventText());
            existingOperation.get().setAttachedCustomerObject(operation.getAttachedCustomerObject());
            existingOperation.get().setAvailableHospitalLocations(operation.getAvailableHospitalLocations());
            existingOperation.get().setAvailablePriorities(operation.getAvailablePriorities());
            existingOperation.get().setBreakpointLocation(operation.getBreakpointLocation());
            existingOperation.get().setCallCenterId(operation.getCallCenterId());
            existingOperation.get().setCaseFolderId(operation.getCaseFolderId());
            existingOperation.get().setCaseInfo(operation.getCaseInfo());
            existingOperation.get().setCreatedTime(operation.getCreatedTime());
            existingOperation.get().setDestinationSiteLocation(operation.getDestinationSiteLocation());
            existingOperation.get().setElectronicKey(operation.getElectronicKey());
            existingOperation.get().setEndTime(operation.getEndTime());
            existingOperation.get().setEventInfo(operation.getEventInfo());
            existingOperation.get().setHeader1(operation.getHeader1());
            existingOperation.get().setHeader2(operation.getHeader2());
            existingOperation.get().setKeyNumber(operation.getKeyNumber());
            existingOperation.get().setLeavePatientLocation(operation.getLeavePatientLocation());
            existingOperation.get().setMedicalCommander(operation.getMedicalCommander());
            existingOperation.get().setMedicalIncidentOfficer(operation.getMedicalIncidentOfficer());
            existingOperation.get().setName(operation.getName());
            existingOperation.get().setOperationID(operation.getOperationID());
            existingOperation.get().setOperationState(operation.getOperationState());
            existingOperation.get().setPatientName(operation.getPatientName());
            existingOperation.get().setPatientUID(operation.getPatientUID());
            existingOperation.get().setRadioGroupMain(operation.getRadioGroupMain());
            existingOperation.get().setRadioGroupSecondary(operation.getRadioGroupSecondary());
            // existingOperation.get().setSelectedHospital(1());
            existingOperation.get().setSelectedHospital(operation.getSelectedHospital());
            // existingOperation.get().setSelectedPriority(1());
            existingOperation.get().setSelectedPriority(operation.getSelectedPriority());
            existingOperation.get().setSendTime(operation.getSendTime());
            existingOperation.get().setTransmitterCode(operation.getTransmitterCode());
            existingOperation.get().setVehicleStatus(operation.getVehicleStatus());
            return evamOperationRepository.save(existingOperation.get());
        }
    }

    /**
     * Retrieves an Operation object by its ID.
     * 
     * @param operationId the ID of the operation to retrieve
     * @return the Operation object with the specified ID, or null if not found
     */
    @Override
    public Operation getById(String id) {
        return evamOperationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No Operation found for id: %s".formatted(id)));
    }
}
