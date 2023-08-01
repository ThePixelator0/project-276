package com.etb.eattrainbalance;

import static org.mockito.BDDMockito.given;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.etb.eattrainbalance.controller.AuthController;
import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    public void init() {
        user = User.builder().email("testEmail@sfu.ca").name("Test Name").password("TestPassword").build();
    }

    @Test
    public void AuthController_Register_ReturnCreated() throws Exception {
        AuthResponse expectedResponse = new AuthResponse();

        given(authService.register(ArgumentMatchers.any(RegisterRequest.class), ArgumentMatchers.any(HttpServletRequest.class)))
                .willReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/register")
                .param("name", "Test Name")
                .param("email", "testEmail@sfu.ca")
                .param("password", "TestPassword"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/dashboard"));
    }

    @Test
    public void AuthController_RegisterAdmin_ReturnCreated() throws Exception {
        AuthResponse expectedResponse = new AuthResponse();

        given(authService.registerAdmin(ArgumentMatchers.any(RegisterRequest.class), ArgumentMatchers.any(HttpServletRequest.class)))
                .willReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/register-admin")
                .param("name", "Admin Name")
                .param("email", "adminEmail@sfu.ca")
                .param("password", "AdminPassword"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/dashboard"));
    }

    @Test
    public void AuthController_Login_ReturnCreated() throws Exception {
        given(authService.login(ArgumentMatchers.any(LoginRequest.class), ArgumentMatchers.any(HttpServletRequest.class)))
                .willReturn(null); // Since login method is not expected to return an object

        mockMvc.perform(post("/api/auth/login")
                .param("email", "testEmail@sfu.ca")
                .param("password", "TestPassword"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/dashboard"));
    }


    @Test
    public void AuthController_Logout_RedirectToRoot() throws Exception {
        mockMvc.perform(post("/api/auth/logout"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
    
    

}
