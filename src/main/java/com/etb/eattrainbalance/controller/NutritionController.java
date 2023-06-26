package com.etb.eattrainbalance.controller;

import com.etb.eattrainbalance.modal.request.NutritionCreateRequest;
import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
@RequiredArgsConstructor
public class NutritionController {
    private final NutritionService nutritionService; // Dependency on NutritionService

    // Endpoint to create a new Nutrition entry
    @PostMapping
    public ResponseEntity<Long> addNutrition(@RequestBody NutritionCreateRequest request) {
        // Call the service layer to add a nutrition and return the id
        return ResponseEntity.ok(nutritionService.addNutrition(request));
    }

    // Endpoint to retrieve a Nutrition entry by ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Nutrition> getNutrition(@PathVariable Long id){
        // Call the service layer to retrieve a nutrition by id
        return ResponseEntity.ok(nutritionService.getNutrition(id));
    }

    // Endpoint to retrieve all Nutrition entries
    @GetMapping("/nutritions")
    public List<Nutrition> getAllNutrition(){
        // Call the service layer to retrieve all nutritions
        return nutritionService.getAllNutrition();
    }

    // Endpoint to update a Nutrition entry by ID
    @PutMapping(value = "/{id}")
    public ResponseEntity<Nutrition> updateNutrition(@PathVariable Long id, @RequestBody NutritionCreateRequest request){
        // Call the service layer to update a nutrition by id and return the updated nutrition
        return ResponseEntity.ok(nutritionService.updateNutrition(id, request));
    }

    // Endpoint to delete a Nutrition entry by ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteNutrition(@PathVariable Long id){
        // Call the service layer to delete a nutrition by id and return the id
        return ResponseEntity.ok(nutritionService.deleteNutrition(id));
    }
}
