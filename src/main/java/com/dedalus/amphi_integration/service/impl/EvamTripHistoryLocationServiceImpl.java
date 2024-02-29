package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamTripLocationHistoryRequestDTO;
import com.dedalus.amphi_integration.model.evam.TripLocationHistory;
import com.dedalus.amphi_integration.repository.EvamTripLocationHistoryRepository;
import com.dedalus.amphi_integration.service.EvamTripLocationHistoryService;

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
        evamTripLocationHistoryRepository.deleteById("1");

        tripLocationHistory.setId("1");
        evamTripLocationHistoryRepository.save(tripLocationHistory);

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
