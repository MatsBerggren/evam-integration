package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestinationControlPointLocation {

        public Double latitude;

        public Double longitude;

        public String name;

        public String additionalInfo;

}
