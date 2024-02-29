package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamOperationListRequestDTO;
import com.dedalus.amphi_integration.model.evam.OperationList;
import com.dedalus.amphi_integration.repository.EvamOperationListRepository;
import com.dedalus.amphi_integration.service.EvamOperationListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class EvamOperationListServiceImpl implements EvamOperationListService {

    @Autowired
    EvamOperationListRepository evamOperationListRepository;

    @Override
    public OperationList updateOperationList(EvamOperationListRequestDTO evamOperationListRequestDTO) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();


        OperationList operationList = gson.fromJson(evamOperationListRequestDTO.getOperationList(), OperationList.class);

        System.out.println("JSON: " + evamOperationListRequestDTO.getOperationList());
        System.out.println("Objekt: " + operationList);

        Optional<OperationList> existingOperationList = evamOperationListRepository.findById("1");

        if (existingOperationList.isEmpty()) {
            operationList.setId("1");
            evamOperationListRepository.save(operationList);
        } else {
            existingOperationList.get().setOperationList(operationList.getOperationList());
            evamOperationListRepository.save(existingOperationList.get());
            System.out.println("Methane Report Updated");
        }

        return operationList;
    }

    @Override
    public OperationList getById(String id) {
        return evamOperationListRepository.findById(id).orElseThrow(() -> new RuntimeException("No OperationList found for id: %s".formatted(id)));
    }
}