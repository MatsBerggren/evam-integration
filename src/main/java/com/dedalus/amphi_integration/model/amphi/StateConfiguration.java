package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateConfiguration {
    private Integer[] allowed_transitions;
    private Integer id;
    private String name;
    private String transition_name;
}
