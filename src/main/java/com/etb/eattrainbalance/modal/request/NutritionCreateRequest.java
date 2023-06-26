package com.etb.eattrainbalance.modal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NutritionCreateRequest {

    private String product; // Name of the product

    private Double calorie; // Calorie content of the product

    private Double protein; // Protein content of the product

    private Double fat; // Fat content of the product

    private Long userId; // The ID of the user associated with this nutrition entry
}
