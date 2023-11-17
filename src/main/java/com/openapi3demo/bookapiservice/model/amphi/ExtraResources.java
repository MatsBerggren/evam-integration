package com.openapi3demo.bookapiservice.model.amphi; 

import com.fasterxml.jackson.annotation.JsonProperty; 

public class ExtraResources{
    public int ambulances;
    public int chemical_suit;
    public int commander_unit;
    public int doctor_on_duty;
    public int emergency_wagon;
    public int helicopter;
    public int medical_team;
    public int medical_transport;
    @JsonProperty("PAM") 
    public int pAM;
    public int sanitation_wagon;
    public int transport_ambulance;
    public int units_total;
}
