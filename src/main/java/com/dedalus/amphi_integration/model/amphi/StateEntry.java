package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
public class StateEntry {
    private Integer distance;
    @Id
    private Integer from_id;
    private String time;
    private Integer to_id;
}
