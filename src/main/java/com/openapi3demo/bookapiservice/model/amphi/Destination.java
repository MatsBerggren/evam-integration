package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Destination {
    private String abbreviation;
    private String name;
    private Position position;
    private String type;
    private Ward[] wards;
}
