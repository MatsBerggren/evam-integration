package com.openapi3demo.bookapiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openapi3demo.bookapiservice.dto.EvamOperationRequestDTO;
import com.openapi3demo.bookapiservice.dto.EvamRakelStateRequestDTO;
import com.openapi3demo.bookapiservice.dto.EvamTripLocationHistoryRequestDTO;
import com.openapi3demo.bookapiservice.dto.EvamVehicleStateRequestDTO;
import com.openapi3demo.bookapiservice.dto.EvamVehicleStatusRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.model.evam.RakelState;
import com.openapi3demo.bookapiservice.model.evam.TripLocationHistory;
import com.openapi3demo.bookapiservice.model.evam.VehicleState;
import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;
import com.openapi3demo.bookapiservice.service.EvamOperationService;
import com.openapi3demo.bookapiservice.service.EvamRakelStateService;
import com.openapi3demo.bookapiservice.service.EvamTripLocationHistoryService;
import com.openapi3demo.bookapiservice.service.EvamVehicleStateService;
import com.openapi3demo.bookapiservice.service.EvamVehicleStatusService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = "application/json", method = RequestMethod.GET)
@Tag(name = "Evam API", description = "API collection for CRUD operations on Book Resource")
public class EvamController {

    @Autowired
    EvamOperationService evamOperationService;
    @Autowired
    EvamVehicleStateService evamVehicleStateService;
    @Autowired
    EvamVehicleStatusService evamVehicleStatusService;
    @Autowired
    EvamRakelStateService evamRakelStateService;
    @Autowired
    EvamTripLocationHistoryService evamTripLocationHistoryService;

    @GetMapping
    public Operation getById(@RequestParam String operationId) {
        return evamOperationService.getById(operationId);
    }

    @PostMapping(value = "/operations", produces = "application/json")
    public Operation createNew(@RequestBody EvamOperationRequestDTO evamOperationRequestDTO) {
        return evamOperationService.updateOperation(evamOperationRequestDTO);
    }

    @PostMapping(value = "/vehiclestate", produces = "application/json")
    public VehicleState createNew(@RequestBody EvamVehicleStateRequestDTO evamVehicleStateRequestDTO) {
        return evamVehicleStateService.updateVehicleState(evamVehicleStateRequestDTO);
    }

    @PostMapping(value = "/rakelstate", produces = "application/json")
    public RakelState createNew(@RequestBody EvamRakelStateRequestDTO evamRakelStateRequestDTO) {
        return evamRakelStateService.updateRakelState(evamRakelStateRequestDTO);
    }

    @PostMapping(value = "/vehiclestatus", produces = "application/json")
    public VehicleStatus[] createNew(@RequestBody EvamVehicleStatusRequestDTO evamVehicleStatusRequestDTO) {
        return evamVehicleStatusService.updateVehicleStatus(evamVehicleStatusRequestDTO);
    }

    @PostMapping(value = "/triplocationhistory", produces = "application/json")
    public TripLocationHistory createNew(
            @RequestBody EvamTripLocationHistoryRequestDTO evamTripLocationHistoryRequestDTO) {
        return evamTripLocationHistoryService.updateTripLocationHistory(evamTripLocationHistoryRequestDTO);
    }
}
