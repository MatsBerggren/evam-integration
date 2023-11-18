package com.openapi3demo.bookapiservice.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.openapi3demo.bookapiservice.model.amphi.Assignment;
import com.openapi3demo.bookapiservice.model.amphi.CloseReason;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.model.evam.OperationState;
import com.openapi3demo.bookapiservice.service.AmphiService;
import com.openapi3demo.bookapiservice.service.EvamService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rest")
@Tag(name = "amPHI API", description = "API collection for CRUD operations on Book Resource")
public class AmphiController {

    @Autowired
    AmphiService amphiService;
    @Autowired
    EvamService evamService;


    @GetMapping(value = "/apiversion", produces = "text/plain")
    public String getCsamInterfaceVersion() {
        return "2.0.0";
    }

    @GetMapping(value = "/unit", produces = "application/json")
    public String getCsamUnit() {
        return "{\"id\":\"dfd4fd99-1fa9-4624-9cfe-05e65ef63fa9\",\"issi\":\"331911\",\"msisdn\":\"3319110\",\"position\":{\"wgs84_dd_la\":-9.25596313493178E+61,\"wgs84_dd_lo\":-9.25596313493178E+61},\"state\":{\"action_name\":\"Idle\",\"allowed_states\":[],\"state_id\":10,\"state_name\":\"Idle\"}}";
    }

    @GetMapping(value = "/destinations", produces = "application/json")
    public String getDestinations() {
        return "[{'abbreviation':'SÖS','name':'Södersjukhuset','position':{'wgs84_dd_la':18.3233342,'wgs84_dd_lo':24.767692},'type':'AkutSjukhus','wards':[{'abbreviation':'','id':'','name':'Akutmottagning','position':{'wgs84_dd_la':18.3233342,'wgs84_dd_lo':24.767692},'status_code':38684},{'abbreviation':'','id':'1','name':'Röntgen','position':{'wgs84_dd_la':18.3233342,'wgs84_dd_lo':24.767692}}]}]";
    }

    @GetMapping(value = "/assignments", produces = "application/json")
    public String getAssignments() {

        Assignment assignment = new Assignment();
        Operation operation = evamService.getByOperationId("56");

        assignment.assignment_number = operation.callCenterId + ":" + operation.caseFolderId + ":"+ operation.operationID;
        assignment.created = operation.createdTime;
        assignment.received = operation.sendTime;
        assignment.accepted = new Date();
        assignment.rowid = UUID.randomUUID().toString();
        assignment.is_closed = Boolean.toString(operation.vehicleStatus.isStartStatus);
        assignment.close_reason = null;
        assignment.is_selected = operation.operationState == OperationState.ACTIVE? "true" : "false";
        assignment.distance = "0";
        assignment.eta = new Date();
        assignment.is_destination_alarm_sent = "false";
        assignment.is_head_unit = "false";
        assignment.is_routed = "false";
        assignment.methane_report = null;
        assignment.position = null;
        assignment.properties = null;
        assignment.rek_report = null;
        assignment.selected_destination = "SÖS";
        assignment.state = null;
        assignment.state_configuration = null;
        assignment.state_entries = null;
        assignment.to_position = null;
        

        Gson gson = new Gson();
        String json = gson.toJson(assignment);

        return json;
    }

}
