package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Unit {
    private String id;
    private String issi;
    private String msisdn;
    private Position position;
    private State state;
}
