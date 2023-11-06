// Define package for this class
package com.programming.projectservice;

import lombok.RequiredArgsConstructor;

// Import necessary classes from the Spring Framework and Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


// Enable service to act as a client in a Eureka service discovery system
@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class ProjectServiceApplication{

	//Main method - entry point of Spring Boot application
	public static void main(String[] args) {

		SpringApplication.run(ProjectServiceApplication.class, args);
	}

}
