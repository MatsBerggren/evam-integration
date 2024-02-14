package com.dedalus.amphi_integration.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CloseReason {
    private String comment;
    private String reason;
}
