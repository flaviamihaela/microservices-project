// Declare package for class
package com.programming.fetchservice;

// Import classes and annotations
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Defines class as entry point for Spring Boot Application
@SpringBootApplication
// Enables class to register with a Eureka discovery server
@EnableEurekaClient
public class FetchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetchServiceApplication.class, args);
	}

}
