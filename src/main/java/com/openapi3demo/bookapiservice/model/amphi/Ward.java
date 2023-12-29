package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ward {
    private String abbreviation;
    private String id;
    private String name;
    private Position position;
    private Integer status_code;
}
