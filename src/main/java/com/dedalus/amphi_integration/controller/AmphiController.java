package com.dedalus.amphi_integration.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dedalus.amphi_integration.classes.LocalDateTimeSerializer;
import com.dedalus.amphi_integration.model.amphi.*;
import com.dedalus.amphi_integration.model.evam.*;
import com.dedalus.amphi_integration.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("/api/rest")
@Tag(name = "amPHI API", description = "API collection for CRUD operations on Amphi Resource")
public class AmphiController {

    @Autowired
    private EvamOperationService evamOperationService;
    @Autowired
    private EvamVehicleStateService evamVehicleStateService;
    @Autowired
    private EvamRakelStateService evamRakelStateService;
    @Autowired
    private AmphiDestinationService amphiDestinationService;
    @Autowired
    private AmphiAssignmentService amphiAssignmentService;
    @Autowired
    private EvamVehicleStatusService evamVehicleStatusService;

    @GetMapping(value = "/apiversion/")
    public String getCsamInterfaceVersion() {
        return "2.0.0";
    }

    @GetMapping(value = "/unit/")
    public String getCsamUnit() {
        String unitId = "1";
        RakelState rakelState = evamRakelStateService.getById(unitId);
        VehicleState vehicleState = evamVehicleStateService.getById(unitId);

        Position position = Position.builder()
                .wgs84_dd_la(vehicleState.getVehicleLocation().getLatitude())
                .wgs84_dd_lo(vehicleState.getVehicleLocation().getLongitude())
                .build();

        Unit unit = Unit.builder()
                .id(rakelState.getUnitId())
                .issi(rakelState.getIssi())
                .msisdn(rakelState.getMsisdn())
                .position(position)
                .state(getState(vehicleState.getVehicleStatus()))
                .build();

        return new Gson().toJson(unit);
    }

    @GetMapping(value = "/destinations/")
    public String getDestinations() {
        Operation operation = evamOperationService.getById("1");
        Gson gson = new Gson();

        ArrayList<Destination> destinations = new ArrayList<>();
        for (HospitalLocation hospitalLocation : operation.availableHospitalLocations) {
            Destination destination =  Destination.builder()
                    .abbreviation(hospitalLocation.getId().toString())
                    .name(hospitalLocation.getName())
                    .position(Position.builder()
                            .wgs84_dd_la(hospitalLocation.getLatitude())
                            .wgs84_dd_lo(hospitalLocation.getLongitude())
                            .build())
                    .type("AkutSjukhus")
                    .wards(new Ward[0])
                    .build();

            destinations.add(destination);
        }
        return gson.toJson(destinations);
    }

    @PostMapping(value = "/destinations/", consumes = "text/plain", produces = "application/json")
    public Destination[] postDestinations(@RequestBody String json) {
        return amphiDestinationService.updateDestinations(new Gson().fromJson(json, Destination[].class));
    }

    @GetMapping(value = "/symbols/")
    public String getSymbols() {
        return new Gson().toJson(new Symbol[1]);
    }

    @PostMapping(value = "/symbols/", consumes = "application/json", produces = "application/json")
    public String postSymbols(@RequestBody Symbol bean) {
        return "OK";
    }

    @GetMapping(value = "/assignments/")
    public String getAssignments() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.disableHtmlEscaping().create();
        return gson.toJson(amphiAssignmentService.getAllAssignments());
    }

    private State getState(VehicleStatus selectedVehicleStatus) {
        VehicleStatus vehicleStatus = evamVehicleStatusService.getByName(selectedVehicleStatus.getName());

        AllowedState[] allowedStates = new AllowedState[]{
                AllowedState.builder().action_name("Hämtat").state_id(3).state_name("*HÄMTAT*").build(),
                AllowedState.builder().action_name("Hemåt").state_id(5).state_name("*HEMÅT*").build(),
                AllowedState.builder().action_name("Klar uppdrag").state_id(6).state_name("*KLAR UPPDRAG*").build(),
                AllowedState.builder().action_name("Uppd. disp.").state_id(7).state_name("*UPPD DISP*").build()
        };

        return State.builder()
                .action_name(vehicleStatus.getName())
                .allowed_states(allowedStates)
                .state_id(Integer.parseInt(vehicleStatus.getId()))
                .state_name(vehicleStatus.getEvent())
                .build();
    }
}