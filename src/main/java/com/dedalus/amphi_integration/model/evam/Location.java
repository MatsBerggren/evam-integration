package com.dedalus.amphi_integration.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private Double latitude;
    private Double longitude;
    private String timestamp;
}
