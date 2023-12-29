package com.openapi3demo.bookapiservice.model.amphi;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MethaneReport {
    private AccessRoad access_road;
    private String created;
    private String exact_location;
    private ExtraResources extra_resources;
    private ArrayList<String> hazards;
    private InventoryLevel inventory_level;
    private String last_updated;
    private Boolean major_incident;
    private Integer numbers_affected_green;
    private Integer numbers_affected_red;
    private Integer numbers_affected_yellow;
    private Position position;
    private String special_injuries;
    private String time_first_departure;
    private ArrayList<String> types;
}
