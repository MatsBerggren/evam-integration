package com.openapi3demo.bookapiservice.model.evam;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operation {
    @Id
    private String id;
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
    public Integer selectedHospital;
    public Integer selectedPriority;
    public OperationState operationState;
    public LeavePatientLocation leavePatientLocation;

    public String getFullId() {
        return this.callCenterId + ":" + this.caseFolderId + ":" + this.operationID;
    }
}
