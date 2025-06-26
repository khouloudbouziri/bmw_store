package com.example.advanced_java_project.Models;


import com.example.advanced_java_project.Enums.FuelType;
import com.example.advanced_java_project.Enums.TransmissionType;
import com.example.advanced_java_project.Enums.VehicleType;
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
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle{

    @Column(name = "engineCapacityCc")
    @Description("Engine capacity")
    private int engineCapacityCc;

    @Column(name = "motorcycleType")
    @Description("Motorcycle type")
    private String motorcycleType;

    @Column(name = "weightKg")
    @Description("Weight in kilograms")
    private double weightKg;

    @Column(name = "hasABS")
    @Description("Indicates if the motorcycle has ABS")
    private boolean hasABS;

    public Motorcycle(Long id, String brand, String model, double price,
                      FuelType fuelType, TransmissionType transmissionType, String color,
                      boolean isAvailable, LocalDate addedDate, String image,
                      int quantity, int engineCapacityCc, String motorcycleType, double weightKg, boolean hasABS) {
        super(id, brand, model, price, fuelType, transmissionType, color, isAvailable, addedDate, image, quantity);
        this.engineCapacityCc = engineCapacityCc;
        this.motorcycleType = motorcycleType;
        this.weightKg = weightKg;
        this.hasABS = hasABS;
    }
}
