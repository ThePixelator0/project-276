package com.etb.eattrainbalance.service;

import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;

// This interface defines the contract for the authentication service
public interface AuthService {

    // Method to register a new user.
    // It takes a RegisterRequest object as input
    // and returns an AuthResponse object which might contain information about the registration status.
    AuthResponse register(RegisterRequest request);

    // Method to log in an existing user.
    // It takes a LoginRequest object and an HttpServletRequest object as input
    // and returns a UserPrincipal object which contains user details loaded from the database.
    UserPrincipal login(LoginRequest loginRequest, HttpServletRequest request);

    UserPrincipal getCurrentUserPrincipal();
}
