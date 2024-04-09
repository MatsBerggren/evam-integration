package com.dedalus.amphi_integration.model.evam;

import java.time.LocalDateTime;
import java.util.Optional;
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
    public String assignedResourceMissionNo;
    public OperationUnit[] operationUnits;

    public String getFullId() {
        String missionNo = "0";
        if (this.assignedResourceMissionNo != null) {
            String[] textSplit = this.assignedResourceMissionNo.split("\u0016");
            if (textSplit.length > 1) {
                missionNo = textSplit[1];
            }
        }
        return this.callCenterId + ":" + this.caseFolderId + ":" + this.operationID + ":" + missionNo;
    }

    public void updateFrom(Operation other) {
        Optional.ofNullable(other.getOperationID()).ifPresent(this::setOperationID);
        Optional.ofNullable(other.getName()).ifPresent(this::setName);
        Optional.ofNullable(other.getSendTime()).ifPresent(this::setSendTime);
        Optional.ofNullable(other.getCreatedTime()).ifPresent(this::setCreatedTime);
        Optional.ofNullable(other.getEndTime()).ifPresent(this::setEndTime);
        Optional.ofNullable(other.getAcceptedTime()).ifPresent(this::setAcceptedTime);
        Optional.ofNullable(other.getCallCenterId()).ifPresent(this::setCallCenterId);
        Optional.ofNullable(other.getCaseFolderId()).ifPresent(this::setCaseFolderId);
        Optional.ofNullable(other.getTransmitterCode()).ifPresent(this::setTransmitterCode);
        Optional.ofNullable(other.getAlarmCategory()).ifPresent(this::setAlarmCategory);
        Optional.ofNullable(other.getAlarmEventCode()).ifPresent(this::setAlarmEventCode);
        Optional.ofNullable(other.getMedicalCommander()).ifPresent(this::setMedicalCommander);
        Optional.ofNullable(other.getMedicalIncidentOfficer()).ifPresent(this::setMedicalIncidentOfficer);
        Optional.ofNullable(other.getAttachedCustomerObject()).ifPresent(this::setAttachedCustomerObject);
        Optional.ofNullable(other.getAlarmEventText()).ifPresent(this::setAlarmEventText);
        Optional.ofNullable(other.getAdditionalInfo()).ifPresent(this::setAdditionalInfo);
        Optional.ofNullable(other.getKeyNumber()).ifPresent(this::setKeyNumber);
        Optional.ofNullable(other.getElectronicKey()).ifPresent(this::setElectronicKey);
        Optional.ofNullable(other.getRadioGroupMain()).ifPresent(this::setRadioGroupMain);
        Optional.ofNullable(other.getRadioGroupSecondary()).ifPresent(this::setRadioGroupSecondary);
        Optional.ofNullable(other.getAdditionalCoordinationInformation()).ifPresent(this::setAdditionalCoordinationInformation);
        Optional.ofNullable(other.getAvailablePriorities()).ifPresent(this::setAvailablePriorities);
        Optional.ofNullable(other.getPatientName()).ifPresent(this::setPatientName);
        Optional.ofNullable(other.getPatientUID()).ifPresent(this::setPatientUID);
        Optional.ofNullable(other.getVehicleStatus()).ifPresent(this::setVehicleStatus);
        Optional.ofNullable(other.getDestinationSiteLocation()).ifPresent(this::setDestinationSiteLocation);
        Optional.ofNullable(other.getBreakpointLocation()).ifPresent(this::setBreakpointLocation);
        Optional.ofNullable(other.getAvailableHospitalLocations()).ifPresent(this::setAvailableHospitalLocations);
        Optional.ofNullable(other.getHeader1()).ifPresent(this::setHeader1);
        Optional.ofNullable(other.getHeader2()).ifPresent(this::setHeader2);
        Optional.ofNullable(other.getEventInfo()).ifPresent(this::setEventInfo);
        Optional.ofNullable(other.getCaseInfo()).ifPresent(this::setCaseInfo);
        Optional.ofNullable(other.getSelectedHospital()).ifPresent(this::setSelectedHospital);
        Optional.ofNullable(other.getSelectedPriority()).ifPresent(this::setSelectedPriority);
        Optional.ofNullable(other.getOperationState()).ifPresent(this::setOperationState);
        Optional.ofNullable(other.getLeavePatientLocation()).ifPresent(this::setLeavePatientLocation);
        Optional.ofNullable(other.getAssignedResourceMissionNo()).ifPresent(this::setAssignedResourceMissionNo);
        Optional.ofNullable(other.getOperationUnits()).ifPresent(this::setOperationUnits);
    }
}
