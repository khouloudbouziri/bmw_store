package com.example.advanced_java_project.Repositories;

import com.example.advanced_java_project.Models.TestDriveReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDriveReservationRepository extends JpaRepository<TestDriveReservation, Long> {
    List<TestDriveReservation> findByUserId(Long userId);
    List<TestDriveReservation> findByVehicleId(Long vehicleId);
}
