package com.dedalus.amphi_integration.service;

import java.util.List;

import com.dedalus.amphi_integration.model.amphi.StateEntry;

public interface AmphiStateEntryService {
    StateEntry updateStateEntry(StateEntry stateEntry);
    List<StateEntry> getAll();
}
