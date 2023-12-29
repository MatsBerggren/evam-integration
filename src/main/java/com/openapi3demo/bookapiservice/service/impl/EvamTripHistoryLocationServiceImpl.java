package com.openapi3demo.bookapiservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openapi3demo.bookapiservice.classes.LocalDateTimeDeserializer;
import com.openapi3demo.bookapiservice.dto.EvamTripLocationHistoryRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.TripLocationHistory;
import com.openapi3demo.bookapiservice.repository.EvamTripLocationHistoryRepository;
import com.openapi3demo.bookapiservice.service.EvamTripLocationHistoryService;

@Service
public class EvamTripHistoryLocationServiceImpl implements EvamTripLocationHistoryService {

    @Autowired
    EvamTripLocationHistoryRepository evamTripLocationHistoryRepository;

    @Override
    public TripLocationHistory updateTripLocationHistory(EvamTripLocationHistoryRequestDTO evamTripLocationHistoryRequestDTO) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();

        TripLocationHistory tripLocationHistory = gson.fromJson(evamTripLocationHistoryRequestDTO.getTripLocationHistory(), TripLocationHistory.class);

        Optional<TripLocationHistory> existingTripLocationHistory = evamTripLocationHistoryRepository.findById("1");

        if (!existingTripLocationHistory.isPresent()) {
            tripLocationHistory.setId("1");
            evamTripLocationHistoryRepository.save(tripLocationHistory);
        } else {
            existingTripLocationHistory.get().setEtaSeconds(tripLocationHistory.getEtaSeconds());
            existingTripLocationHistory.get().setLocationHistory(tripLocationHistory.getLocationHistory());
        }

        return tripLocationHistory;
    }

    @Override
    public TripLocationHistory getById(String id) {
        return evamTripLocationHistoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No TripLocationHistory found for id: %s".formatted(id)));
    }

    @Override
    public List<TripLocationHistory> getAll() {
        return evamTripLocationHistoryRepository.findAll();
    }

}
