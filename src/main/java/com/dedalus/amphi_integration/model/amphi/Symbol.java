package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Symbol {
    private String id;
    private Integer mapitemtype;
    private State state;
    private double heading;
    private String description;
    private String assignmentId;
    private Position position;
    private String unitId;
    private boolean is_deleted;
    private String icon;
    private Integer color;
    private String geometry;
}
