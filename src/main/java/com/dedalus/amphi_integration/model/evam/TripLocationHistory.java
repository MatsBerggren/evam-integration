package com.dedalus.amphi_integration.model.evam;

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
