package com.openapi3demo.bookapiservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openapi3demo.bookapiservice.classes.LocalDateTimeSerializer;
import com.openapi3demo.bookapiservice.model.amphi.AccessRoad;
import com.openapi3demo.bookapiservice.model.amphi.AllowedState;
import com.openapi3demo.bookapiservice.model.amphi.Assignment;
import com.openapi3demo.bookapiservice.model.amphi.CloseReason;
import com.openapi3demo.bookapiservice.model.amphi.CommandOrganization;
import com.openapi3demo.bookapiservice.model.amphi.Destination;
import com.openapi3demo.bookapiservice.model.amphi.ExtraResources;
import com.openapi3demo.bookapiservice.model.amphi.IncidentOrganization;
import com.openapi3demo.bookapiservice.model.amphi.InventoryLevel;
import com.openapi3demo.bookapiservice.model.amphi.MethaneReport;
import com.openapi3demo.bookapiservice.model.amphi.Position;
import com.openapi3demo.bookapiservice.model.amphi.Property;
import com.openapi3demo.bookapiservice.model.amphi.RekReport;
import com.openapi3demo.bookapiservice.model.amphi.State;
import com.openapi3demo.bookapiservice.model.amphi.StateConfiguration;
import com.openapi3demo.bookapiservice.model.amphi.StateEntry;
import com.openapi3demo.bookapiservice.model.amphi.Symbol;
import com.openapi3demo.bookapiservice.model.amphi.ToPosition;
import com.openapi3demo.bookapiservice.model.amphi.Unit;
import com.openapi3demo.bookapiservice.model.amphi.Ward;
import com.openapi3demo.bookapiservice.model.evam.HospitalLocation;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.model.evam.OperationState;
import com.openapi3demo.bookapiservice.model.evam.RakelState;
import com.openapi3demo.bookapiservice.model.evam.VehicleState;
import com.openapi3demo.bookapiservice.model.evam.VehicleStatus;
import com.openapi3demo.bookapiservice.service.AmphiDestinationService;
import com.openapi3demo.bookapiservice.service.AmphiStateEntryService;
import com.openapi3demo.bookapiservice.service.EvamOperationService;
import com.openapi3demo.bookapiservice.service.EvamRakelStateService;
import com.openapi3demo.bookapiservice.service.EvamVehicleStateService;
import com.openapi3demo.bookapiservice.service.EvamVehicleStatusService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rest")

@Tag(name = "amPHI API", description = "API collection for CRUD operations on Book Resource")
public class AmphiController {

        @Autowired
        EvamOperationService evamOperationService;
        @Autowired
        EvamVehicleStateService evamVehicleStateService;
        @Autowired
        EvamVehicleStatusService evamVehicleStatusService;
        @Autowired
        EvamRakelStateService evamRakelStateService;
        @Autowired
        AmphiDestinationService amphiDestinationService;
        @Autowired
        AmphiStateEntryService amphiStateEntryService;

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
              .position(com.openapi3demo.bookapiservice.model.amphi.Position.builder()
                .wgs84_dd_la(hospitalLocation.getLatitude())
                .wgs84_dd_lo(hospitalLocation.getLongitude())
                .build())
              .type("AkutSjukhus")
              .wards(new Ward[0])
              .build();
        
            destinations.add(destination);
          }
          String json = gson.toJson(destinations);
        
          return json;
        }

        @PostMapping(value = "/destinations/", consumes = "text/plain", produces = "application/json")
        public String postDestinations(HttpServletRequest request, @RequestBody String json) {
            Gson gson = new Gson();
            Destination[] destinations = gson.fromJson(json, Destination[].class);
            
            Arrays.stream(destinations).forEach(amphiDestinationService::updateDestination);
            
            return gson.toJson(destinations);
        }
        
        @GetMapping(value = "/symbols/")
        public String getSymbols() {
                Symbol[] symbols = new Symbol[1];
                Gson gson = new Gson();
                String responce = gson.toJson(symbols);

                return responce;
        }

        @PostMapping(value = "/symbols/", consumes = "application/json", produces = "application/json")
        public String postSymbols(HttpServletRequest request, @RequestBody Symbol bean) {
                return "OK";
        }

        @GetMapping(value = "/assignments/")
        public String getAssignments() {

                Operation operation = evamOperationService.getById("1");

                Assignment[] assignments = new Assignment[1];
                Assignment assignment = Assignment.builder()
                    .assignment_number(operation.getFullId())
                    .close_reason(CloseReason.builder()
                        .comment("1")
                        .reason("Patient saknades").build())
                    .created("2023-10-25T14:30:00Z")
                    .received("2023-10-25T14:31:00Z")
                    .accepted("2023-10-25T14:32:00Z")
                    .rowid("0faaa8e0-956b-444b-baea-ddf6e806bc8d")
                    .is_closed(Boolean.toString(operation.operationState != OperationState.ACTIVE))
                    .is_selected(operation.operationState == OperationState.ACTIVE ? "1" : "0")
                    .is_destination_alarm_sent("false")
                    .selected_destination(operation.getSelectedHospital().toString())
                    .eta("2023-10-25T14:33:00Z")
                    .is_head_unit("false")
                    .is_routed("false")
                    .distance("0")
                    .methane_report(getMethaneReport(operation))
                    .rek_report(getRekReport(operation))
                    .position(null)
                    .to_position(ToPosition.builder()
                        .wgs84_dd_la(operation.destinationSiteLocation.getLatitude())
                        .wgs84_dd_lo(operation.destinationSiteLocation.getLongitude()).build())
                    .properties(getProperties(operation))
                    .state(getState(operation.getVehicleStatus()))
                    .state_entries(getStateEntries(operation))
                    .state_configuration(getStateConfiguration(operation))
                    .build();
                assignments[0] = assignment;

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
                Gson gson = gsonBuilder.disableHtmlEscaping().create();

                String json = gson.toJson(assignments);

                return json;
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

        private StateConfiguration[] getStateConfiguration(Operation operation) {
            List<VehicleStatus> vehicleStatuses = evamVehicleStatusService.getAll();

            return vehicleStatuses.stream()
                    .map(vehicleStatus -> StateConfiguration.builder()
                            .id(Integer.parseInt(vehicleStatus.getId()))
                            .name(vehicleStatus.getName())
                            .transition_name(vehicleStatus.getEvent())
                            .allowed_transitions(new Integer[]{1, 2, 3, 4, 5, 6, 7})
                            .build())
                    .toArray(StateConfiguration[]::new);
        }

        private StateEntry[] getStateEntries(Operation operation) {
            return amphiStateEntryService.getAll().toArray(new StateEntry[0]);
        }

        private RekReport getRekReport(Operation operation) {
            CommandOrganization commandOrganization = CommandOrganization.builder()
                    .hq_commander("")
                    .hq_commander_medical("")
                    .medical_commander("")
                    .section_commander_1_incident_site("")
                    .section_commander_2_incident_site("")
                    .section_commander_assembly_point("")
                    .section_commander_breakpoint("")
                    .section_commander_collect_point("")
                    .build();

            IncidentOrganization incidentOrganization = IncidentOrganization.builder()
                    .assembly_point_injured("")
                    .assembly_point_uninjured("")
                    .assembly_site("")
                    .breakpoint("")
                    .collect_point("")
                    .command_site("")
                    .incident_site("")
                    .landing_site("")
                    .build();

            Position position = Position.builder()
                    .wgs84_dd_la(operation.getDestinationSiteLocation().getLatitude())
                    .wgs84_dd_lo(operation.getDestinationSiteLocation().getLongitude())
                    .build();

            return RekReport.builder()
                    .affected_count("")
                    .command_organization(commandOrganization)
                    .comments("")
                    .created("2015-03-31T14:14:00.353Z")
                    .incident_organization(incidentOrganization)
                    .last_updated("2015-03-31T14:44:34.934Z")
                    .position(position)
                    .resources_on_site("")
                    .build();
        }

        private MethaneReport getMethaneReport(Operation operation) {
            AccessRoad accessRoad = AccessRoad.builder().comment("").is_obstructed(false).build();
            ExtraResources extraResources = ExtraResources.builder()
                    .ambulances(0).chemical_suit(0).commander_unit(0).doctor_on_duty(0)
                    .emergency_wagon(0).helicopter(0).medical_team(0).medical_transport(0).PAM(0)
                    .sanitation_wagon(0).transport_ambulance(0).units_total(0).build();
            String[] hazards = new String[]{"Halka", "Rök/Gas"};
            String[] levels = new String[]{"0", "1_3", "2_3", "3_3"};
            InventoryLevel inventoryLevel = InventoryLevel.builder().levels(levels).selected_level_index(1).build();
            Position position = Position.builder().wgs84_dd_la(59.338985).wgs84_dd_lo(18.06327).build();
            String[] types = new String[]{"Trafikolycka"};

            MethaneReport methaneReport = MethaneReport.builder()
                    .access_road(accessRoad)
                    .created("2023-10-25T14:34:00Z")
                    .exact_location(operation.getDestinationSiteLocation().getStreet())
                    .extra_resources(extraResources)
                    .hazards(hazards)
                    .inventory_level(inventoryLevel)
                    .last_updated("2023-10-25T14:35:00Z")
                    .major_incident(false)
                    .numbers_affected_green(0)
                    .numbers_affected_red(0)
                    .numbers_affected_yellow(0)
                    .position(position)
                    .special_injuries("")
                    .time_first_departure("2015-03-31T14:45:00Z")
                    .types(types)
                    .build();

            return methaneReport;
        }

        private ArrayList<Property> getProperties(Operation operation) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
                ArrayList<Property> properties = new ArrayList<Property>();

                String[] s = operation.getPatientName().split(" ");

                properties.add(Property.builder().name("created").value("2011-12-12 16:31").build());
                properties.add(Property.builder().name("sender").value(Objects.toString(operation.getTransmitterCode(), "")).build());
                properties.add(Property.builder().name("central").value(Objects.toString(operation.getCallCenterId(), "")).build());
                properties.add(Property.builder().name("area").value(Objects.toString(null, "")).build());
                properties.add(Property.builder().name("caseindex1").value(Objects.toString(operation.getAlarmCategory(), "")).build());
                properties.add(Property.builder().name("caseindex2").value(Objects.toString(operation.getHeader1(), "")).build());
                properties.add(Property.builder().name("caseindex3").value(Objects.toString(operation.getHeader2(), "")).build());
                properties.add(Property.builder().name("city").value(Objects.toString(operation.getDestinationSiteLocation().getLocality(), "")).build());
                properties.add(Property.builder().name("comment").value(Objects.toString(operation.getCaseInfo(), "")).build());
                properties.add(Property.builder().name("firstname").value(Objects.toString(s[0], "")).build());
                properties.add(Property.builder().name("ib").value(Objects.toString(operation.getOperationID(), "")).build());
                properties.add(Property.builder().name("id").value(Objects.toString(operation.getCaseFolderId(), "")).build());
                properties.add(Property.builder().name("lastname").value(Objects.toString(s[1], "")).build());
                properties.add(Property.builder().name("municipality").value(Objects.toString(operation.getDestinationSiteLocation().getMunicipality(), "")).build());
                properties.add(Property.builder().name("personid").value(Objects.toString(operation.getPatientUID(), "")).build());
                properties.add(Property.builder().name("positionwgs84")
                                .value(Objects.toString(
                                                "La=" + operation.getDestinationSiteLocation().getLatitude() + " Lo=" + operation.getDestinationSiteLocation().getLongitude(), ""))
                                .build());
                properties.add(Property.builder().name("priority").value(Objects.toString(operation.getSelectedPriority().toString(), "")).build());
                properties.add(Property.builder().name("priorityin").value(Objects.toString(operation.getSelectedPriority().toString(), "")).build());
                properties.add(Property.builder().name("radiogroup").value(Objects.toString(operation.getRadioGroupMain(), "")).build());
                properties.add(Property.builder().name("radiogroupname").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("routedirections").value(Objects.toString(operation.getDestinationSiteLocation().getRouteDirections(), "")).build());
                properties.add(Property.builder().name("street").value(Objects.toString(operation.getDestinationSiteLocation().getStreet(), "")).build());
                properties.add(Property.builder().name("toaddress").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("tocity").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("toposition").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("sendtime").value(Objects.toString(operation.getSendTime().format(dateTimeFormatter), "")).build());
                properties.add(Property.builder().name("pickuptime").value(Objects.toString(operation.getDestinationSiteLocation().getPickupTime(), "")).build());
                properties.add(Property.builder().name("toarea").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("tomunicipality").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("departuretime").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("topositionrt90").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("allradiogroups").value(Objects.toString(operation.getRadioGroupSecondary(), "")).build());
                properties.add(Property.builder().name("additionalcoordinationinformation").value(Objects.toString(operation.getAdditionalCoordinationInformation(), "")).build());
                properties.add(Property.builder().name("additionalinfo").value(Objects.toString(operation.getAdditionalInfo(), "")).build());
                properties.add(Property.builder().name("objectid").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("alarmcategory").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("alarmeventcode").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("areanumberandphonenumber").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("assignedresourceincasefolder").value(Objects.toString("", "")).build());
                properties.add(Property.builder().name("testassignment").value(Objects.toString("true", "")).build());

                return properties;
        }

        public String dateFix(LocalDateTime localDateTime) {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Stockholm"));

                String returnDate = zonedDateTime.format(dateTimeFormatter);

                return returnDate;
        }

        public String dateFixShort(LocalDateTime localDateTime) {

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Stockholm"));

                String returnDate = zonedDateTime.format(dateTimeFormatter);

                return returnDate;
        }

}
