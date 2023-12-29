package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
public class TripLocationHistory {
    @Id
    private String id;
    private Location[] locationHistory;
    private Integer etaSeconds;
}
