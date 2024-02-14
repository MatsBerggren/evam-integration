package com.dedalus.amphi_integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dedalus.amphi_integration.dto.EvamOperationRequestDTO;
import com.dedalus.amphi_integration.dto.EvamRakelStateRequestDTO;
import com.dedalus.amphi_integration.dto.EvamTripLocationHistoryRequestDTO;
import com.dedalus.amphi_integration.dto.EvamMethaneReportRequestDTO;
import com.dedalus.amphi_integration.dto.EvamVehicleStateRequestDTO;
import com.dedalus.amphi_integration.dto.EvamVehicleStatusRequestDTO;
import com.dedalus.amphi_integration.model.amphi.MethaneReport;
import com.dedalus.amphi_integration.model.evam.Operation;
import com.dedalus.amphi_integration.model.evam.RakelState;
import com.dedalus.amphi_integration.model.evam.TripLocationHistory;
import com.dedalus.amphi_integration.model.evam.VehicleState;
import com.dedalus.amphi_integration.model.evam.VehicleStatus;
import com.dedalus.amphi_integration.service.EvamMethaneReportService;
import com.dedalus.amphi_integration.service.EvamOperationService;
import com.dedalus.amphi_integration.service.EvamRakelStateService;
import com.dedalus.amphi_integration.service.EvamTripLocationHistoryService;
import com.dedalus.amphi_integration.service.EvamVehicleStateService;
import com.dedalus.amphi_integration.service.EvamVehicleStatusService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = "application/json", method = RequestMethod.GET)
@Tag(name = "Evam API", description = "API collection for CRUD operations on Evam Resource")
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
    @Autowired
    EvamMethaneReportService evamMethaneReportService;

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

    @PostMapping(value = "/methanereport", produces = "application/json")
    public MethaneReport createNew(
            @RequestBody EvamMethaneReportRequestDTO evamMethaneReportRequestDTO) {
        return evamMethaneReportService.updateMethaneReport(evamMethaneReportRequestDTO);
    }
}
