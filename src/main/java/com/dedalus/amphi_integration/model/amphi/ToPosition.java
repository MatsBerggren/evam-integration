package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToPosition {
    private Double wgs84_dd_la;
    private Double wgs84_dd_lo;
}
