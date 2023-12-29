package com.openapi3demo.bookapiservice.model.evam;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemHealth {
    public boolean isHealthy;
    public String message;
    public LocalDateTime timestamp;
}
