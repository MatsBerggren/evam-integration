package com.openapi3demo.bookapiservice.model.evam;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(value = "operations")
public class Operation {
        public String operationID;
        public String name;
        public Date sendTime;
        public Date createdTime;
        public Date endTime;
        // Rakel
        public String callCenterId;
        public String caseFolderId;
        // Misc
        public String transmitterCode;
        public String alarmCategory;
        public String alarmEventCode;
        public String medicalCommander; // incidentCommander
        public String medicalIncidentOfficer;
        public String attachedCustomerObject;
        public String alarmEventText;
        public String additionalInfo;
        public String keyNumber;
        public String electronicKey;
        // Radio groups
        public String radioGroupMain;
        public String radioGroupSecondary;
        // Extra
        public String additionalCoordinationInformation;
        // Priority
        public OperationPriority[] availablePriorities;
        // Patient
        public String patientName;
        public String patientUID;
        // status, destinations
        public VehicleStatus vehicleStatus;
        public DestinationSiteLocation destinationSiteLocation;
        public DestinationControlPointLocation breakpointLocation;
        public HospitalLocation[] availableHospitalLocations;
        // Case index 2, 3, event description, comment
        public String header1;
        public String header2;
        public String eventInfo;
        public String caseInfo;
        public int selectedHospital;
        public int selectedPriority;
        public OperationState operationState;
        public LeavePatientLocation leavePatientLocation;
    }
