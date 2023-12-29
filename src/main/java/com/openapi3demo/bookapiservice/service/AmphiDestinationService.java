package com.openapi3demo.bookapiservice.service;

import com.openapi3demo.bookapiservice.model.amphi.Destination;

public interface AmphiDestinationService {
    Destination updateDestination(Destination destination);

    Destination getByNameAndType(String name, String type);
}
