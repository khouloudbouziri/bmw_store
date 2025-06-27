package com.example.advanced_java_project.Services;

import com.example.advanced_java_project.Dtos.VehicleDTO;
import com.example.advanced_java_project.Enums.TransmissionType;
import com.example.advanced_java_project.Enums.VehicleType;
import com.example.advanced_java_project.Models.Car;
import com.example.advanced_java_project.Models.Motorcycle;
import com.example.advanced_java_project.Models.Truck;
import com.example.advanced_java_project.Models.Vehicle;
import com.example.advanced_java_project.Repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public List<Vehicle> getAllVehicules() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    @Transactional
    public List<Vehicle> getVehiclesByTransmissionType(TransmissionType transmissionType) {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream()
                .filter(vehicle -> vehicle.getTransmissionType().equals(transmissionType))
                .collect(Collectors.toList());
    }

    @Transactional
    public Vehicle addVehicle(VehicleDTO vehicle) {
        VehicleType vehicleType = vehicle.getVehicleType();
        switch (vehicleType) {
            case car:
                Car car = new Car();
                car.setNumberOfDoors(vehicle.getNumberOfDoors());
                car.setNumberOfSeats(vehicle.getNumberOfSeats());
                populateCommonFields(car, vehicle);
                return vehicleRepository.save(car);
            case truck:
                Truck truck = new Truck();
                truck.setMaxLoadKg(vehicle.getMaxLoadKg());
                truck.setCargoVolumeM3(vehicle.getCargoVolumeM3());
                truck.setNumberOfAxles(vehicle.getNumberOfAxles());
                populateCommonFields(truck, vehicle);
                return vehicleRepository.save(truck);
            case motorcycle:
                Motorcycle motorcycle = new Motorcycle();
                motorcycle.setEngineCapacityCc(vehicle.getEngineCapacityCc());
                motorcycle.setMotorcycleType(vehicle.getMotorcycleType());
                motorcycle.setWeightKg(vehicle.getWeightKg());
                motorcycle.setHasABS(vehicle.isHasABS());
                populateCommonFields(motorcycle, vehicle);
                return vehicleRepository.save(motorcycle);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
    }

    @Transactional
    public Vehicle updateVehicle(Long id, VehicleDTO newVehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));

        VehicleType vehicleType = newVehicleDTO.getVehicleType();
        populateCommonFields(vehicle, newVehicleDTO);

        switch (vehicleType) {
            case car:
                if (!(vehicle instanceof Car)) {
                    throw new IllegalStateException("Vehicle is not instance of Car");
                }
                Car car = (Car) vehicle;
                car.setNumberOfDoors(newVehicleDTO.getNumberOfDoors());
                car.setNumberOfSeats(newVehicleDTO.getNumberOfSeats());
                break;

            case truck:
                if (!(vehicle instanceof Truck)) {
                    throw new IllegalStateException("Vehicle is not instance of Truck");
                }
                Truck truck = (Truck) vehicle;
                truck.setMaxLoadKg(newVehicleDTO.getMaxLoadKg());
                truck.setCargoVolumeM3(newVehicleDTO.getCargoVolumeM3());
                truck.setNumberOfAxles(newVehicleDTO.getNumberOfAxles());
                break;

            case motorcycle:
                if (!(vehicle instanceof Motorcycle)) {
                    throw new IllegalStateException("Vehicle is not instance of Motorcycle");
                }
                Motorcycle moto = (Motorcycle) vehicle;
                moto.setEngineCapacityCc(newVehicleDTO.getEngineCapacityCc());
                moto.setMotorcycleType(newVehicleDTO.getMotorcycleType());
                moto.setWeightKg(newVehicleDTO.getWeightKg());
                moto.setHasABS(newVehicleDTO.isHasABS());
                break;

            default:
                throw new UnsupportedOperationException("Unknown vehicle type: " + vehicleType);
        }

        return vehicleRepository.save(vehicle);
    }


    @Transactional
    public void deleteVehicule(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()) {
            vehicleRepository.delete(vehicle.get());
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

    private static void populateCommonFields(Vehicle vehicle, VehicleDTO dto) {
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setPrice(dto.getPrice());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setTransmissionType(dto.getTransmissionType());
        vehicle.setColor(dto.getColor());
        vehicle.setAvailable(dto.isAvailable());
        vehicle.setAddedDate(LocalDate.now());
        vehicle.setImage(dto.getImage());
        vehicle.setQuantity(dto.getQuantity());
    }

    public boolean vehicleExists(Long id) {
        return vehicleRepository.existsById(id);
    }
}
