package com.dedalus.amphi_integration.model.evam;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationList {
    @Id
    private String id;
    public Operation[] operationList;
}
