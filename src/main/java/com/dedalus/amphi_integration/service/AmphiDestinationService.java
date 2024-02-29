package com.dedalus.amphi_integration.service;

import com.dedalus.amphi_integration.model.amphi.Destination;

public interface AmphiDestinationService {
    Destination[] updateDestinations(Destination[] destinations);

    Destination getByNameAndType(String name, String type);
}
