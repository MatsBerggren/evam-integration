package com.openapi3demo.bookapiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openapi3demo.bookapiservice.dto.EvamOperationRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.Operation;
import com.openapi3demo.bookapiservice.service.EvamService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Evam API", description = "API collection for CRUD operations on Book Resource")
public class EvamController {

    @Autowired
    EvamService evamService;

    @GetMapping
    public Operation getByOperationId(@RequestParam String operationId){
        return evamService.getByOperationId(operationId);
    }

    @PostMapping(value = "/operations", produces = "application/json")
    public Operation createNew(@RequestBody EvamOperationRequestDTO evamOperationRequestDTO){
        return evamService.createNew(evamOperationRequestDTO);
    }

}
