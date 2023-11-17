package com.openapi3demo.bookapiservice.model.amphi; 

import java.util.ArrayList;
import java.util.Date;

public class MethaneReport{
    public AccessRoad access_road;
    public Date created;
    public String exact_location;
    public ExtraResources extra_resources;
    public ArrayList<String> hazards;
    public InventoryLevel inventory_level;
    public Date last_updated;
    public boolean major_incident;
    public int numbers_affected_green;
    public int numbers_affected_red;
    public int numbers_affected_yellow;
    public Position position ;
    public String special_injuries;
    public Date time_first_departure;
    public ArrayList<String> types ;
}
