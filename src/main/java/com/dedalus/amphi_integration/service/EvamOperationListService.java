package com.dedalus.amphi_integration.service;

import com.dedalus.amphi_integration.dto.EvamOperationListRequestDTO;
import com.dedalus.amphi_integration.model.evam.OperationList;

public interface EvamOperationListService {
    OperationList updateOperationList(EvamOperationListRequestDTO evamOperationListRequestDTO);
    OperationList getById(String id);
}
