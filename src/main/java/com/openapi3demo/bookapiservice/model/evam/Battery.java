package com.openapi3demo.bookapiservice.model.evam;

enum BatteryHealth {
    UNKNOWN,
    GOOD,
    OVERHEAT,
    DEAD,
    OVER_VOLTAGE,
    UNSPECIFIED_FAILURE,
    COLD;
}

enum BatteryPlugged {
    DOCK,
    AC,
    USB,
    WIRELESS;
}

enum BatteryStatus {
    UNKNOWN,
    CHARGING,
    DISCHARGING,
    NOT_CHARGING,
    FULL;
}


public class Battery {
        public BatteryHealth health;
        public BatteryPlugged plugged;
        public BatteryStatus status;
        public Number capacity;
}
