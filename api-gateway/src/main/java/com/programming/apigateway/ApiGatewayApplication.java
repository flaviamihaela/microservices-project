// Define package for class
package com.programming.apigateway;

// Import classes and annotations
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Defines class as entry point for Spring Boot Application
@SpringBootApplication
// Enables application to register with Eureka discovery server 
@EnableEurekaClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
