// Declare package for class
package com.programming.discoveryserver;
// Import classes and annotations
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Defines class as entry point for Spring Boot Application 
@SpringBootApplication
// Enables class to register with Eureka discovery server
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}