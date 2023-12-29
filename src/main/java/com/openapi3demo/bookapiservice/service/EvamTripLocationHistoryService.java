package com.openapi3demo.bookapiservice.service;

import java.util.List;

import com.openapi3demo.bookapiservice.dto.EvamTripLocationHistoryRequestDTO;
import com.openapi3demo.bookapiservice.model.evam.TripLocationHistory;

public interface EvamTripLocationHistoryService {
    TripLocationHistory updateTripLocationHistory(EvamTripLocationHistoryRequestDTO evamTripLocationHistoryRequestDTO);

    List<TripLocationHistory> getAll();

    TripLocationHistory getById(String id);
}
