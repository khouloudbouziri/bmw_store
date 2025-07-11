package com.example.advanced_java_project.Services;

import com.example.advanced_java_project.Enums.TestDriveReservationStatus;
import com.example.advanced_java_project.Models.TestDriveReservation;
import com.example.advanced_java_project.Repositories.TestDriveReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDriveReservationService {
    private TestDriveReservationRepository testDriveReservationRepository;

    public TestDriveReservationService(TestDriveReservationRepository testDriveReservationRepository) {
        this.testDriveReservationRepository = testDriveReservationRepository;
    }

    public List<TestDriveReservation> getAllReservations() {
        return testDriveReservationRepository.findAll();
    }

    public List<TestDriveReservation> getReservationsByUserId(Long userId) {
        return testDriveReservationRepository.findByUserId(userId);
    }

    public List<TestDriveReservation> getReservationsByVehicleId(Long vehicleId) {
        return testDriveReservationRepository.findByVehicleId(vehicleId);
    }

    public TestDriveReservation getReservationById(Long id) {
        return testDriveReservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Test Drive Reservation not found with ID: " + id));
    }

    @Transactional
    public TestDriveReservation createReservation(TestDriveReservation reservation) {
        return testDriveReservationRepository.save(reservation);
    }

    @Transactional
    public TestDriveReservation updateReservation(Long id, TestDriveReservation reservationDetails) {
        TestDriveReservation existingReservation = getReservationById(id);

        if (reservationDetails.getStatus().equals(TestDriveReservationStatus.CANCELLED)) {
            testDriveReservationRepository.delete(existingReservation);
            return null;
        }

        existingReservation.setUser(reservationDetails.getUser());
        existingReservation.setVehicle(reservationDetails.getVehicle());
        existingReservation.setAppointment_date(reservationDetails.getAppointment_date());
        existingReservation.setStatus(reservationDetails.getStatus());

        return testDriveReservationRepository.save(existingReservation);
    }

}
