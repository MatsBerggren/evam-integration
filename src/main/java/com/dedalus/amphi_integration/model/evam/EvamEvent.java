package com.dedalus.amphi_integration.model.evam;

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
