package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private Double rt90_x;
    private Double rt90_y;
    private Double sweref99_e;
    private Double sweref99_n;
    private Double wgs84_dd_la;
    private Double wgs84_dd_lo;
}
