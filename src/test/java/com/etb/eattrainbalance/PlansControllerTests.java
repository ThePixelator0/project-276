package com.etb.eattrainbalance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.etb.eattrainbalance.controller.PlansController;
import com.etb.eattrainbalance.models.PlansRepository;
import com.etb.eattrainbalance.persistence.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlansController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PlansControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlansRepository plansRepo;

    @MockBean
    private UserRepository userRepo;

    @Test
    public void testAddPlan() throws Exception {
        mockMvc.perform(post("/plans/add")
                .param("planTitle", "Test Plan")
                .param("userId", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/plans"));
    }

    @Test
    public void testDeletePlan() throws Exception {
        mockMvc.perform(post("/plans/delete")
                .param("planId", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/plans"));
    }
}
