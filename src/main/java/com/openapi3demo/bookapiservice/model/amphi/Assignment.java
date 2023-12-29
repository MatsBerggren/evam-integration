package com.openapi3demo.bookapiservice.model.amphi;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Assignment {
    private String accepted;
    private String assignment_number;
    private CloseReason close_reason;
    private String created;
    private String distance;
    private String eta;
    private String is_closed;
    private String is_destination_alarm_sent;
    private String is_head_unit;
    private String is_routed;
    private String is_selected;
    private MethaneReport methane_report;
    private Position position;
    private ArrayList<Property> properties;
    private String received;
    private RekReport rek_report;
    private String rowid;
    private String selected_destination;
    private State state;
    private StateConfiguration[] state_configuration;
    private StateEntry[] state_entries;
    private ToPosition to_position;
}
