package com.example.advanced_java_project.Models;

import com.example.advanced_java_project.Enums.TestDriveReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_drive_reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDriveReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointment_date;

    @Column(name = "status")
    private TestDriveReservationStatus status;
}
