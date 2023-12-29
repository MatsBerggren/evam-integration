package com.openapi3demo.bookapiservice.model.amphi;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateConfiguration {
    private ArrayList<Integer> allowed_transitions;
    private Integer id;
    private String name;
    private String transition_name;
}
