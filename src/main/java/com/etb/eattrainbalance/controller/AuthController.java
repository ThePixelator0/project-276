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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticate;

    // This method handles HTTP POST requests to /api/auth/register for user registration.
    /*@PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticate.register(request));
    }*/

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestParam("name") String name, @Valid @RequestParam("email") String email, @Valid @RequestParam("password") String password, HttpServletRequest request) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(name);
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        return ResponseEntity.ok(authenticate.register(registerRequest, request));
    }

    // This method handles HTTP POST requests to /api/auth/login for user login.
    @PostMapping("/login")
    public ResponseEntity<UserPrincipal> login(@Valid @RequestParam("email") String email, @RequestParam("password") String password, final HttpServletRequest request) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        return ResponseEntity.ok(authenticate.login(loginRequest, request));
    }

}
