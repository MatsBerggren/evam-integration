package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Symbol {
    private String assignmentId;
    private String description;
    private double heading;
    private String id;
    private boolean is_deleted;
    private Integer mapitemtype;
    private Position position;
    private String state;
    private String icon;
    private Integer color;
    private String geometry;
}
