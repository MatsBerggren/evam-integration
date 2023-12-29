package com.openapi3demo.bookapiservice.service;

import java.util.List;

import com.openapi3demo.bookapiservice.model.amphi.StateEntry;

public interface AmphiStateEntryService {
    StateEntry updateStateEntry(StateEntry stateEntry);
    List<StateEntry> getAll();
}
