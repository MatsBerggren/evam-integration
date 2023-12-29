package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
public class VehicleStatus {
    @Id
    private String id;
    private String name;
    private String event;
    private String successorName;
    private Boolean isStartStatus;
    private Boolean isEndStatus;
    private String categoryType;
    private String categoryName;
}
