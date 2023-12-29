package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessRoad {
    private String comment;
    private boolean is_obstructed;
}
