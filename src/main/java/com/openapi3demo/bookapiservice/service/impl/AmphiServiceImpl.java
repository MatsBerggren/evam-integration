package com.openapi3demo.bookapiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openapi3demo.bookapiservice.model.amphi.Assignment;
import com.openapi3demo.bookapiservice.repository.AmphiRepository;
import com.openapi3demo.bookapiservice.service.AmphiService;

@Service
public class AmphiServiceImpl implements AmphiService {

    @Autowired
    AmphiRepository amphiRepository;

}
