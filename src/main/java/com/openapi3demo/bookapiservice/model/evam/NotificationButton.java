package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationButton {
        public String label;
        public String callback;
    }