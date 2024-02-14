package com.dedalus.amphi_integration.model.evam;

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
        public Integer capacity;
}
