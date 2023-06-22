package com.etb.eattrainbalance.controller;


import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticate;

    // This method handles HTTP POST requests to /api/auth/register for user registration.
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticate.register(request));
    }

    // This method handles HTTP POST requests to /api/auth/login for user login.
    @PostMapping("/login")
    public ResponseEntity<UserPrincipal> login(@Valid @RequestBody LoginRequest loginRequest, final HttpServletRequest request) {
        return ResponseEntity.ok(authenticate.login(loginRequest, request));
    }

}
