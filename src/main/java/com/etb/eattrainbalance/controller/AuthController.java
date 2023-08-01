package com.etb.eattrainbalance.controller;


import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticate;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestParam("name") String name, @Valid @RequestParam("email") String email, @Valid @RequestParam("password") String password, HttpServletRequest request) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(name);
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);

        AuthResponse authResponse = authenticate.register(registerRequest, request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/dashboard");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }



    @PostMapping("/register-admin")
    public ResponseEntity<Void> registerAdmin(@Valid @RequestParam("name") String name, @Valid @RequestParam("email") String email, @Valid @RequestParam("password") String password, HttpServletRequest request) {
        
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(name);
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        ResponseEntity.ok(authenticate.registerAdmin(registerRequest, request));
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/dashboard");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    // This method handles HTTP POST requests to /api/auth/login for user login.
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        ResponseEntity.ok(authenticate.login(loginRequest, request));
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/dashboard");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        
        return "redirect:/";
    }

}
