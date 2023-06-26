package com.etb.eattrainbalance.service;

import com.etb.eattrainbalance.modal.request.NutritionCreateRequest;
import com.etb.eattrainbalance.persistence.entity.Nutrition;

import java.util.List;


public interface NutritionService{
    String addNutrition(NutritionCreateRequest request);
    Nutrition getNutrition(Long id);
    String updateNutrition(Long id, NutritionCreateRequest request);
    String deleteNutrition(Long id);
    List<Nutrition> getAllNutrition();
}