package com.dedalus.amphi_integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedalus.amphi_integration.model.amphi.Destination;
import com.dedalus.amphi_integration.repository.AmphiDestinationRepository;
import com.dedalus.amphi_integration.service.AmphiDestinationService;

@Service
public class AmphiDestinationServiceImpl implements AmphiDestinationService {

    @Autowired
    AmphiDestinationRepository amphiDestinationRepository;

    @Override
    public Destination updateDestination(Destination destination) {
        Destination existingDestination = amphiDestinationRepository.findByNameAndType(destination.getName(),
                destination.getType());
        if (existingDestination == null) {
            return amphiDestinationRepository.save(destination);
        } else {
            existingDestination.setName(destination.getName());
            existingDestination.setType(destination.getType());
            existingDestination.setAbbreviation(destination.getAbbreviation());
            existingDestination.setPosition(destination.getPosition());
            existingDestination.setWards(destination.getWards());
            return amphiDestinationRepository.save(existingDestination);
        }
    }

    @Override
    public Destination getByNameAndType(String name, String type) {
        return amphiDestinationRepository.findByNameAndType(name, type);
    }

}
