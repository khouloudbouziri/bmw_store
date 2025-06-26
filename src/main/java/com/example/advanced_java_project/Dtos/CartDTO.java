package com.example.advanced_java_project.Dtos;

import com.example.advanced_java_project.Models.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    @OneToOne
    private Long userId;

    @JsonProperty("vehicles")
    @ManyToOne
    private List<CartItemDTO> items;

    @JsonProperty("total_price")
    private double totalPrice;
}
