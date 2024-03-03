package com.dedalus.amphi_integration.service.impl;

import java.util.List;
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
    public Destination[] updateDestinations(Destination[] destinations) {
        System.out.println(destinations);
        amphiDestinationRepository.deleteAll();
        for (Destination destination : destinations) {
            amphiDestinationRepository.save(destination);
        } 
        return destinations;
    }

    @Override
    public Destination getByNameAndType(String name, String type) {
        return amphiDestinationRepository.findByNameAndType(name, type);
    }

    @Override
    public List<Destination> getAllDestinations() {
        return amphiDestinationRepository.findAll();
    }

}
