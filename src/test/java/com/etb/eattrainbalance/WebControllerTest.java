package com.etb.eattrainbalance;

import com.etb.eattrainbalance.controller.WebController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebController webController;

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    public void shouldReturnIndex() throws Exception {
        Mockito.when(webController.defaultPage()).thenReturn("index");

        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    public void shouldReturnRegister() throws Exception {
        Mockito.when(webController.getRegisterPage()).thenReturn("register");
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }


    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    public void shouldReturnRegisterAdmin() throws Exception {
        Mockito.when(webController.getRegisterAdminPage()).thenReturn("register-admin");

        this.mockMvc.perform(get("/register-admin"))
            .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    public void shouldReturnLogin() throws Exception {
        Mockito.when(webController.getLoginPage()).thenReturn("login");

        this.mockMvc.perform(get("/login"))
            .andExpect(status().isOk());
    }
    
    // @WithMockUser(username = "jdj2@sfu.ca", roles = {"Admin"})
    // @Test
    // public void shouldReturnNutritionTracker() throws Exception {
    //     Mockito.when(webController.defaultPage()).thenReturn("nutrition-tracker");

    //     this.mockMvc.perform(get("/nutrition-tracker"))
    //         .andExpect(status().isOk());
    // }
}
