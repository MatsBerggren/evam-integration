package com.dedalus.amphi_integration.model.evam;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleState {
    @Id
    private String id;
    public LocalDateTime timestamp;
    public VehicleStatus vehicleStatus;
    public String activeCaseFullId;
    public Location vehicleLocation;
}
