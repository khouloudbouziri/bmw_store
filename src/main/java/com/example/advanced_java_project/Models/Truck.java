package com.example.advanced_java_project.Models;

import com.example.advanced_java_project.Enums.FuelType;
import com.example.advanced_java_project.Enums.TransmissionType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle{

    @Column(name = "maxLoadKg")
    @Description("Maximum load capacity in kilograms")
    private double maxLoadKg;

    @Column(name = "cargoVolumeM3")
    @Description("Cargo volume in cubic meters")
    private double cargoVolumeM3;

    @Column(name = "numberOfAxles")
    @Description("Number of axles in the truck")
    private int numberOfAxles;

    public Truck(Long id, String brand, String model, double price,
                 FuelType fuelType, TransmissionType transmissionType, String color,
                 boolean isAvailable, LocalDate addedDate, String image,
                 int quantity, double maxLoadKg, double cargoVolumeM3, int numberOfAxles) {
        super(id, brand, model, price, fuelType, transmissionType, color, isAvailable, addedDate, image, quantity);
        this.maxLoadKg = maxLoadKg;
        this.cargoVolumeM3 = cargoVolumeM3;
        this.numberOfAxles = numberOfAxles;
    }
}
