package com.example.advanced_java_project.Controllers;

import com.example.advanced_java_project.Models.TestDriveReservation;
import com.example.advanced_java_project.Services.TestDriveReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test-drive-reservations")
public class TestDriveReservationController {
    private TestDriveReservationService testDriveReservationService;

    public TestDriveReservationController(TestDriveReservationService testDriveReservationService) {
        this.testDriveReservationService = testDriveReservationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestDriveReservation>> getAllReservations() {
        List<TestDriveReservation> reservations = testDriveReservationService.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestDriveReservation>> getReservationsByUserId(Long userId) {
        List<TestDriveReservation> reservations = testDriveReservationService.getReservationsByUserId(userId);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reservations);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<TestDriveReservation>> getReservationsByVehicleId(Long vehicleId) {
        List<TestDriveReservation> reservations = testDriveReservationService.getReservationsByVehicleId(vehicleId);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reservations);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    @PutMapping("/update/{reservationId}")
    public ResponseEntity<TestDriveReservation> updateReservationStatus(Long reservationId, TestDriveReservation reservation) {
        TestDriveReservation existingReservation = testDriveReservationService.getReservationById(reservationId);
        if (existingReservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestDriveReservation updatedReservation = testDriveReservationService.updateReservation(existingReservation.getId(), reservation);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReservation);
    }
}
