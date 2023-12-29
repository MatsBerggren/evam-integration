package com.openapi3demo.bookapiservice.model.evam;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
