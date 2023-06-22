// Specifies the package in which this class resides.
package com.etb.eattrainbalance;

// Importing the SpringApplication class from the Spring framework.
import org.springframework.boot.SpringApplication;

// Importing the SpringBootApplication annotation.
// This is a convenience annotation that adds all of the following: @Configuration, @EnableAutoConfiguration, @ComponentScan.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Declaring the main class of the application.
@SpringBootApplication
public class EatTrainBalanceApplication {
    public static void main(String[] args) {
        // Launches the Spring Boot application.
        // The run method returns an ApplicationContext where all the Spring-managed beans are registered.
        // The args parameter can be used to accept command-line arguments.
        SpringApplication.run(EatTrainBalanceApplication.class, args);
    }
}
