package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessRoad {
    private String comment;
    private boolean is_obstructed;
}
