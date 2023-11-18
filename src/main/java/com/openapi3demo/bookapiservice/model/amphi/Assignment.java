package com.openapi3demo.bookapiservice.model.amphi; 

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class Assignment{
    public Date accepted;
    public String assignment_number;
    public CloseReason close_reason;
    public Date created;
    public String distance;
    public Date eta;
    public String is_closed;
    public String is_destination_alarm_sent;
    public String is_head_unit;
    public String is_routed;
    public String is_selected;
    public MethaneReport methane_report;
    public Position position;
    public ArrayList<Property> properties;
    public Date received;
    public RekReport rek_report;
    public String rowid;
    public String selected_destination;
    public State state;
    public ArrayList<StateConfiguration> state_configuration;
    public ArrayList<StateEntry> state_entries;
    public ToPosition to_position;
}
