package com.example.advanced_java_project.Dtos;

import com.example.advanced_java_project.Enums.TestDriveReservationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TestDriveReservationDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("vehicle_id")
    private Long vehicleId;

    @JsonProperty("appointment_date")
    private LocalDateTime appointmentDate;

    @JsonProperty("status")
    private TestDriveReservationStatus status;
}
