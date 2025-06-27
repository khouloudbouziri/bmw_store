package com.example.advanced_java_project.Models;

import com.example.advanced_java_project.Enums.FuelType;
import com.example.advanced_java_project.Enums.TransmissionType;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.time.LocalDate;

@Table(name = "vehicles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "vehicle_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "MOTORCYCLE"),
        @JsonSubTypes.Type(value = Truck.class, name = "TRUCK")
})
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    @Description("Vehicle ID")
    private Long id;

    @Column(name="brand", nullable = false)
    @Description("Vehicle brand")
    private String brand;

    @Column(name="model", nullable = false)
    @Description("Vehicle model")
    private String model;

    @Column(name="price", nullable = false)
    @Description("Vehicle price")
    private double price;

    @Column(name="fuelType", nullable = false)
    @Description("Vehicle fuel type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name="transmissionType", nullable = false)
    @Description("Vehicle transmission type")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Column(name="color", nullable = false)
    @Description("Vehicle color")
    private String color;

    @Column(name="isAvailable", nullable = false)
    @Description("Vehicle availability status")
    private boolean isAvailable;

    @Column(name="addedDate", nullable = false)
    @Description("Vehicle added date")
    private LocalDate addedDate;

    @Column(name = "vehicleImage")
    @Description("Vehicle image")
    @Lob
    private String image;

    @Column(name = "quantity")
    @Description("Vehicle available quantity")
    private int quantity;
}
