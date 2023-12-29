package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalLocation {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String name;
    private String street1;
    private String city;
    private String region;
    private String postalCode;
}
