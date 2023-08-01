package com.etb.eattrainbalance;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.etb.eattrainbalance.controller.FoodController;
import com.etb.eattrainbalance.models.Food;
import com.etb.eattrainbalance.models.Plans;
import com.etb.eattrainbalance.models.PlansRepository;

import java.util.ArrayList;
import java.util.Optional;

@WebMvcTest(FoodController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FoodControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlansRepository plansRepo;

    @Test
    public void testSubmitFood() throws Exception {
        // Prepare a mock plan
        Plans plan = new Plans();
        plan.setFoods(new ArrayList<>());
        when(plansRepo.findById(anyInt())).thenReturn(Optional.of(plan));

        mockMvc.perform(post("/submitFood")
                .param("foodName", "Apple")
                .param("foodColor", "Red")
                .param("foodDay", "Monday")
                .param("planId", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/plans"));

        // Verify that the food was added to the plan
        assertThat(plan.getFoods()).hasSize(1);
        Food addedFood = plan.getFoods().get(0);
        assertThat(addedFood.getName()).isEqualTo("Apple");
        assertThat(addedFood.getColor()).isEqualTo("Red");
        assertThat(addedFood.getDay()).isEqualTo("Monday");
    }
}
