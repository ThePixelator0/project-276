package com.etb.eattrainbalance.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

// The @Slf4j annotation is a Lombok annotation that provides a logger for the class.
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // This method is overridden from AuthenticationEntryPoint and is called 
    // whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        // Log the error message
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());
        // Send an HTTP 401 Unauthorized error code to the client along with the exception message.
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                e.getLocalizedMessage());
    }
}
