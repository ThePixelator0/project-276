package com.etb.eattrainbalance.service.impl;

import com.etb.eattrainbalance.modal.request.LoginRequest;
import com.etb.eattrainbalance.modal.request.RegisterRequest;
import com.etb.eattrainbalance.modal.response.AuthResponse;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.entity.enam.Role;
import com.etb.eattrainbalance.persistence.repository.UserRepository;
import com.etb.eattrainbalance.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Method to register a new user
    @Transactional // This annotation ensures that the method is executed within a transaction
    @Override
    public AuthResponse register(RegisterRequest registerRequest, HttpServletRequest request) {
        // Building a new User object from the registration request
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .enable(true)
                .resetPassword(false)
                .build();
        
        // Save the user to the database
        user =  userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                registerRequest.getEmail(),
                registerRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        //UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        // Return a response containing the user's ID
        return new AuthResponse(user.getId());
    }

    @Transactional // This annotation ensures that the method is executed within a transaction
    @Override
    public AuthResponse registerAdmin(RegisterRequest registerRequest, HttpServletRequest request) {
        // Building a new User object from the registration request
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .enable(true)
                .resetPassword(false)
                .build();
        
        // Save the user to the database
        user =  userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                registerRequest.getEmail(),
                registerRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        //UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        // Return a response containing the user's ID
        return new AuthResponse(user.getId());
    }

    // Method to log in an existing user
    @Override
    public UserPrincipal login(LoginRequest loginRequest, HttpServletRequest request) {
        // Authenticating the user with username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Storing authentication object in SecurityContextHolder (security context)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Getting the authenticated user's details
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        // Storing security context in HttpSession
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return userPrincipal;
    }

    @Override
    public UserPrincipal getCurrentUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
