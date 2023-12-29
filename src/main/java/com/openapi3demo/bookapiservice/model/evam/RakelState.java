package com.openapi3demo.bookapiservice.model.evam;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RakelState {
    @Id
    private String id;
    private String unitId;
    private String msisdn;
    private String issi;
    private String gssi;
}
