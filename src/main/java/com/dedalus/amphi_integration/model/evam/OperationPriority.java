package com.dedalus.amphi_integration.model.evam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationPriority {
    public Integer id;
    public String name;
}
