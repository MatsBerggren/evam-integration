package com.openapi3demo.bookapiservice.model.amphi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandOrganization {
    private String hq_commander;
    private String hq_commander_medical;
    private String medical_commander;
    private String section_commander_1_incident_site;
    private String section_commander_2_incident_site;
    private String section_commander_assembly_point;
    private String section_commander_breakpoint;
    private String section_commander_collect_point;
}
