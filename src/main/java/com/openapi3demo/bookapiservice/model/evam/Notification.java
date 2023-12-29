package com.openapi3demo.bookapiservice.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
        public String heading;
        public String description;
        public NotificationType notificationType;
        public NotificationButton primaryButton;
        public NotificationButton secondaryButton;
    }
