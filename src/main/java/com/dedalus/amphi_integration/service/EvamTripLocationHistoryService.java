package com.dedalus.amphi_integration.service;

import java.util.List;
import com.dedalus.amphi_integration.dto.EvamTripLocationHistoryRequestDTO;
import com.dedalus.amphi_integration.model.evam.TripLocationHistory;

public interface EvamTripLocationHistoryService {
    TripLocationHistory updateTripLocationHistory(EvamTripLocationHistoryRequestDTO evamTripLocationHistoryRequestDTO);

    List<TripLocationHistory> getAll();

    TripLocationHistory getById(String id);
}
