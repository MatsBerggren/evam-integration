package com.dedalus.amphi_integration.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedalus.amphi_integration.model.amphi.StateEntry;
import com.dedalus.amphi_integration.repository.AmphiStateEntryRepository;
import com.dedalus.amphi_integration.service.AmphiStateEntryService;

@Service
public class AmphiStateEntryServiceImpl implements AmphiStateEntryService {

    @Autowired
    AmphiStateEntryRepository amphiStateEntryRepository;

    @Override
    public StateEntry updateStateEntry(StateEntry stateEntry) {
        Optional<StateEntry> existingStateEntry = amphiStateEntryRepository
                .findById(stateEntry.getFrom_id().toString());

        if (existingStateEntry.isEmpty()) {
            return amphiStateEntryRepository.save(stateEntry);
        } else {
            existingStateEntry.get().setDistance(stateEntry.getDistance());
            existingStateEntry.get().setFrom_id(stateEntry.getFrom_id());
            existingStateEntry.get().setTime(stateEntry.getTime());
            existingStateEntry.get().setTo_id(stateEntry.getTo_id());

            return amphiStateEntryRepository.save(existingStateEntry.get());
        }
    }

    @Override
    public List<StateEntry> getAll() {
        return amphiStateEntryRepository.findAll();
    }
}
