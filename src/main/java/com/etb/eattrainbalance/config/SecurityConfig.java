package com.etb.eattrainbalance.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// RequiredArgsConstructor is a Lombok annotation that generates a constructor with required final fields
@RequiredArgsConstructor
// Indicates that this class contains @Bean definition methods
@Configuration
// Enables Spring Security’s web security support
@EnableWebSecurity
// Allows securing methods using method-level annotations
@EnableMethodSecurity
public class SecurityConfig {

    // Array holding the whitelisted endpoints that do not require authentication
    private static final String[] AUTH_WHITELIST = {
            // -- Authentication
            "/api/auth/login",
            "/api/auth/register", // todo for testing REMOVE!
            "/nutrition",
            "/dashboard",
            // -- Static resources
            "/",
            "/css/**",
            "/js/**"
    };

    // UserDetailsService is used to retrieve user-related data
    private final UserDetailsService userDetailsService;

    // Defines the security filter chain bean, configuring request handling and authentication
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .cors()
                .and()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeHttpRequests(
                        requests -> requests.
                                requestMatchers(AUTH_WHITELIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated()

                )
                .sessionManagement()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    // Configures the PasswordEncoder bean to encode passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures the AuthenticationManager bean to handle authentication
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
