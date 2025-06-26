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
@DiscriminatorValue("CAR")
public class Car extends Vehicle{

    @Column(name = "numberOfDoors")
    @Description("Number of doors in the car")
    private int numberOfDoors;

    @Column(name = "numberOfSeats")
    @Description("Number of seats in the car")
    private int numberOfSeats;

    public Car(Long id, String brand, String model, double price,
               FuelType fuelType, TransmissionType transmissionType, String color,
               boolean isAvailable, LocalDate addedDate, String image, int quantity,
               int numberOfDoors, int numberOfSeats) {
        super(id, brand, model, price, fuelType, transmissionType, color, isAvailable, addedDate, image, quantity);
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }
}
