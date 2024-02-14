package com.dedalus.amphi_integration.model.evam; 

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeavePatientLocation {
        public Double latitude;
        public Double longitude;
        public String street;
        public String locality;
        public String municipality;
        public String routeDirections;
        public String leaveTime;
}
