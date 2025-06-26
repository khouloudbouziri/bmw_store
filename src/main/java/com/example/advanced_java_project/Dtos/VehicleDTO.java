package com.example.advanced_java_project.Dtos;

import com.example.advanced_java_project.Enums.FuelType;
import com.example.advanced_java_project.Enums.TransmissionType;
import com.example.advanced_java_project.Enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("price")
    private double price;

    @JsonProperty("fuelType")
    private FuelType fuelType;

    @JsonProperty("transmissionType")
    private TransmissionType transmissionType;

    @JsonProperty("color")
    private String color;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @JsonProperty("addedDate")
    private LocalDate addedDate;

    @JsonProperty("vehicleImage")
    private String image;

    @JsonProperty("vehicleType")
    private VehicleType vehicleType;

    @JsonProperty("quantity")
    private int quantity;

    //Car attributes
    @JsonProperty("numberOfDoors")
    private int numberOfDoors;

    @JsonProperty("numberOfSeats")
    private int numberOfSeats;

    //Truck attributes
    @JsonProperty("maxLoadKg")
    private double maxLoadKg;

    @JsonProperty("cargoVolumeM3")
    private double cargoVolumeM3;

    @JsonProperty("numberOfAxles")
    private int numberOfAxles;

    //Motorcycle attributes
    @JsonProperty("engineCapacityCc")
    private int engineCapacityCc;

    @JsonProperty("motorcycleType")
    private String motorcycleType;

    @JsonProperty("weightKg")
    private double weightKg;

    @JsonProperty("hasABS")
    private boolean hasABS;

}
