package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllowedState {
    private String action_name;
    private Integer state_id;
    private String state_name;
}
