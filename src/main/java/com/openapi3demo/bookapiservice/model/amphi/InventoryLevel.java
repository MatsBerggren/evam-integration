package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryLevel {
    private String[] levels;
    private Integer selected_level_index;
}
