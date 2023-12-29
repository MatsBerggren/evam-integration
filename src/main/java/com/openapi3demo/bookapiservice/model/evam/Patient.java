package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {

    public String name;
    public String uid;
}
