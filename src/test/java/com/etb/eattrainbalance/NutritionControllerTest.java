package com.etb.eattrainbalance;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.etb.eattrainbalance.controller.NutritionController;
import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.persistence.repository.NutritionRepository;

@WebMvcTest(NutritionController.class)
public class NutritionControllerTest {
    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private NutritionRepository nutritionRepository;

    @Test
    public void testAddNutrition() throws Exception{
        Map<String, String> foodData = new HashMap<>();
        //change soon with real food-code
        foodData.put("food-code", "12345");
        foodData.put("meal-type", "Breakfast");
        foodData.put("user-id", "6");
        foodData.put("food-item", "Some Food");

        mockMvc.perform(post("/api/nutrition/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("food-code", foodData.get("food-code"))
        .param("meal-type", foodData.get("meal-type"))
        .param("user-id", foodData.get("user-id"))
        .param("food-item", foodData.get("food-item")))
        .andExpect(status().isFound())
        .andExpect(header().string("Location", "/nutrition"));

        verify(nutritionRepository).save(any(Nutrition.class));
    }*/

}
