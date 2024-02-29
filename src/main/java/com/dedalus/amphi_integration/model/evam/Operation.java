package com.dedalus.amphi_integration.model.evam;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operation {
    @Id
    private String id;
    public String amPHIUniqueId;
    public String operationID;
    public String name;
    public LocalDateTime sendTime;
    public LocalDateTime createdTime;
    public LocalDateTime endTime;
    public LocalDateTime acceptedTime;
    public String callCenterId;
    public String caseFolderId;
    public String transmitterCode;
    public String alarmCategory;
    public String alarmEventCode;
    public String medicalCommander;
    public String medicalIncidentOfficer;
    public String attachedCustomerObject;
    public String alarmEventText;
    public String additionalInfo;
    public String keyNumber;
    public String electronicKey;
    public String radioGroupMain;
    public String radioGroupSecondary;
    public String additionalCoordinationInformation;
    public OperationPriority[] availablePriorities;
    public String patientName;
    public String patientUID;
    public VehicleStatus vehicleStatus;
    public DestinationSiteLocation destinationSiteLocation;
    public DestinationControlPointLocation breakpointLocation;
    public HospitalLocation[] availableHospitalLocations;
    public String header1;
    public String header2;
    public String eventInfo;
    public String caseInfo;
    public String selectedHospital;
    public String selectedPriority;
    public OperationState operationState;
    public LeavePatientLocation leavePatientLocation;

    public String getFullId() {
        return this.callCenterId + ":" + this.caseFolderId + ":" + this.operationID;
    }

    public void updateFrom(Operation other) {
        this.setOperationID(other.getOperationID());
        this.setAdditionalCoordinationInformation(other.getAdditionalCoordinationInformation());
        this.setAdditionalInfo(other.getAdditionalInfo());
        this.setAlarmCategory(other.getAlarmCategory());
        this.setAlarmEventCode(other.getAlarmEventCode());
        this.setAlarmEventText(other.getAlarmEventText());
        this.setAttachedCustomerObject(other.getAttachedCustomerObject());
        this.setAvailableHospitalLocations(other.getAvailableHospitalLocations());
        this.setAvailablePriorities(other.getAvailablePriorities());
        this.setBreakpointLocation(other.getBreakpointLocation());
        this.setCallCenterId(other.getCallCenterId());
        this.setCaseFolderId(other.getCaseFolderId());
        this.setCaseInfo(other.getCaseInfo());
        this.setCreatedTime(other.getCreatedTime());
        this.setDestinationSiteLocation(other.getDestinationSiteLocation());
        this.setElectronicKey(other.getElectronicKey());
        this.setEndTime(other.getEndTime());
        this.setEventInfo(other.getEventInfo());
        this.setHeader1(other.getHeader1());
        this.setHeader2(other.getHeader2());
        this.setKeyNumber(other.getKeyNumber());
        this.setLeavePatientLocation(other.getLeavePatientLocation());
        this.setMedicalCommander(other.getMedicalCommander());
        this.setMedicalIncidentOfficer(other.getMedicalIncidentOfficer());
        this.setName(other.getName());
        this.setOperationID(other.getOperationID());
        this.setOperationState(other.getOperationState());
        this.setPatientName(other.getPatientName());
        this.setPatientUID(other.getPatientUID());
        this.setRadioGroupMain(other.getRadioGroupMain());
        this.setRadioGroupSecondary(other.getRadioGroupSecondary());
        this.setSelectedHospital(other.getSelectedHospital());
        this.setSelectedPriority(other.getSelectedPriority());
        this.setSendTime(other.getSendTime());
        this.setTransmitterCode(other.getTransmitterCode());
        this.setVehicleStatus(other.getVehicleStatus());
    }
}
