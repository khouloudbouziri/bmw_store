package com.example.advanced_java_project.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    @JsonProperty("id")
    private Long vehicleId;

    @JsonProperty("quantity")
    private int quantity;

}
