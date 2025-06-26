package com.example.advanced_java_project.Controllers;

import com.example.advanced_java_project.Dtos.VehicleDTO;
import com.example.advanced_java_project.Enums.TransmissionType;
import com.example.advanced_java_project.Models.Vehicle;
import com.example.advanced_java_project.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.advanced_java_project.Utils.Constants;

import java.util.List;

@RestController
@RequestMapping(Constants.VEHICLE_PATH)
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<Object> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicules();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Object> getVehicleById(
            @PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/vehicles/Transmissiontype/{vehicleTransmissionType}")
    public ResponseEntity<Object> getVehiclesByTransmissionType(
            @PathVariable TransmissionType vehicleTransmissionType) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByTransmissionType(vehicleTransmissionType);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Object> addVehicle(
            @RequestBody VehicleDTO vehicleDTO) {
        Vehicle savedVehicle = vehicleService.addVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<Object> updateVehicle(
            @PathVariable Long id,
            @RequestBody VehicleDTO vehicleDTO) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }

    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
