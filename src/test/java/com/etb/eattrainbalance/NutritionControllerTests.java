package com.etb.eattrainbalance;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.etb.eattrainbalance.controller.AuthController;
import com.etb.eattrainbalance.controller.NutritionController;
import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.NutritionRepository;
import com.etb.eattrainbalance.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(controllers = NutritionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class NutritionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NutritionRepository nutritionRepository;

    @MockBean
    private RestTemplate restTemplate;

    private User user;

    @BeforeEach
    public void init() {
        user = User.builder().email("testEmail@sfu.ca").name("Test Name").password("TestPassword").build();
    }

    @Test
    public void getAllNutrition_ShouldReturnListOfNutrition() throws Exception {
        when(nutritionRepository.findByUserIdAndMealType(anyLong(), anyString()))
            .thenReturn(Arrays.asList(new Nutrition()));

        mockMvc.perform(get("/api/nutrition/allNutrition/Breakfast/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getTotalCalorieCount_ShouldReturnTotalCalorieCount() throws Exception {
        List<Nutrition> nutritions = new ArrayList<>();
        Nutrition nutrition = new Nutrition();
        nutrition.setCalorie("100");
        nutritions.add(nutrition);

        when(nutritionRepository.findByUserId(anyLong())).thenReturn(nutritions);

        mockMvc.perform(get("/api/nutrition/totalCalorieCount/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void deleteNutrition_ShouldDeleteNutritionAndRedirect() throws Exception {
        mockMvc.perform(get("/api/nutrition/deleteNutrition/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:/nutrition"));

        verify(nutritionRepository).deleteById(1L);
    }


}