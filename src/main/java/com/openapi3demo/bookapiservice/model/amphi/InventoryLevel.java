package com.openapi3demo.bookapiservice.model.amphi;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryLevel {
    private ArrayList<String> levels;
    private Integer selected_level_index;
}
