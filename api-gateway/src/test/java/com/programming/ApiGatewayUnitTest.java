// Package for test class
package com.programming;

// Import classes and annotations
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.programming.apigateway.ApiGatewayApplication;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.SpringApplication;

// Annotation - allows test to run with full application context
@SpringBootTest
public class ApiGatewayUnitTest {
    // Autowire ApplicationContext - checks if Spring context loaded properly    
    @Autowired
    private ApplicationContext applicationContext;

    // Test method ensures application context loaded
    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }
    
    // Test method simulates starting of main application
    // Ensures application can start with specified configuration and classes
    @Test
    public void mainApplicationStart() {
        String[] args = {}; // Empty arguments for application start
        try (ConfigurableApplicationContext context = SpringApplication.run(ApiGatewayApplication.class, args)) {
            
            // Assert application context loads successfully
            assertNotNull(context);
            assertTrue(context.isRunning());
        }
    }
        
}

