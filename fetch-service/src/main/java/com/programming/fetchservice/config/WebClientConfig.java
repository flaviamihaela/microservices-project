// Define package for class
package com.programming.fetchservice.config;

// Import classes and annotations
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// Indicates that class has bean deifinition methods
@Configuration
public class WebClientConfig {

    // Tells Spring method produces bean to be managed by Spring container
    @Bean
    // Configures WebClient with load balancer to help distribute load among different instances of services
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        // Method returns a WebClient.Builder used to create WebClient instances
        return WebClient.builder();
    }
}
