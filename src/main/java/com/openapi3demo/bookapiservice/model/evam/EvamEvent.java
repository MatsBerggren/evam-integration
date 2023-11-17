package com.openapi3demo.bookapiservice.model.evam;

enum EvamEvent {
    NewOrUpdatedOperation,
    NewOrUpdatedSettings,
    NewOrUpdatedInternetState,
    NewOrUpdatedDeviceRole,
    NewOrUpdatedLocation,
    NewOrUpdatedVehicleState,
    NewOrUpdatedTripLocationHistory,
    VehicleServicesNotificationSent,
    VehicleServicesNotificationCallbackTriggered,
    GRPCEstablished,
    NewOrUpdatedOperationList,
    NewOrUpdatedBattery,
    NewOrUpdatedDisplayMode,
    OSVersionSet,
    VehicleServicesVersionSet,
    AppVersionSet,
    DeviceIdSet,
    AppIdSet,
    _testEvent;
}
