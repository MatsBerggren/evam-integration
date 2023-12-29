package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtraResources {
    public Integer ambulances;
    public Integer chemical_suit;
    public Integer commander_unit;
    public Integer doctor_on_duty;
    public Integer emergency_wagon;
    public Integer helicopter;
    public Integer medical_team;
    public Integer medical_transport;
    public Integer PAM;
    public Integer sanitation_wagon;
    public Integer transport_ambulance;
    public Integer units_total;
}
