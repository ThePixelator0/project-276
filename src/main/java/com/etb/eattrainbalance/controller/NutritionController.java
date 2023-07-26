package com.etb.eattrainbalance.controller;
import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.persistence.repository.NutritionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/nutrition")
@RequiredArgsConstructor
public class NutritionController {
    @Autowired
    private NutritionRepository nutritionRepository;
    // Endpoint to create a new Nutrition entry
    @PostMapping("/add")
    public ResponseEntity<Void> addNutrition(@RequestParam Map<String, String> foodData, HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();

        String foodCode = foodData.get("food-code");

        //may need name of food but not that important
        String apiUrl = "https://api.nal.usda.gov/fdc/v1/food/" + foodCode + "?format=abridged&api_key=e1Qeo8EhfrLVg7E8hvBXp4eOYhnaOudpgeXLkGml";

        ResponseEntity<String> foodItem = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);

        //Need to get specific json values
        //System.out.println(foodItem.getBody());
        String foodJson = foodItem.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode protein;
        JsonNode fat;
        JsonNode calories;
        
        try{
            JsonNode foodNode = objectMapper.readTree(foodJson);
            JsonNode nutrientsArray = foodNode.get("foodNutrients");
            
            protein = nutrientsArray.get(0).get("amount");
            fat = nutrientsArray.get(1).get("amount");
            calories = nutrientsArray.get(3).get("amount");
            //protein(g), fat(g), calories

        } catch(IOException e){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", e.toString());
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }

        String mealType = foodData.get("meal-type");
        Long userId = Long.parseLong(foodData.get("user-id"));
        String foodName = foodData.get("food-item");

        Nutrition nutrition = new Nutrition();
        nutrition.setMealType(mealType);
        nutrition.setProduct(foodName);
        nutrition.setUserId(userId);
        nutrition.setProtein(protein.toString());
        nutrition.setCalorie(calories.toString());
        nutrition.setFat(fat.toString());
        nutrition.setCreationDateTime(LocalDateTime.now());
        nutritionRepository.save(nutrition);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/nutrition");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    // Endpoint to retrieve all Nutrition entries
    @GetMapping("/allNutrition/{mealType}/{userId}")
    //change to return a list
    public List getAllNutrition(@PathVariable String mealType, @PathVariable Long userId, Model model){
        List<Nutrition> nutritions = nutritionRepository.findByUserIdAndMealType(userId, mealType);
        
        return nutritions;
    }

    // Endpoint to delete a Nutrition entry by ID
    @GetMapping("/deleteNutrition/{id}")
    public String deleteNutrition(@PathVariable Long id){
    
        nutritionRepository.deleteById(id);
        return "redirect:/nutrition";
    }
}
