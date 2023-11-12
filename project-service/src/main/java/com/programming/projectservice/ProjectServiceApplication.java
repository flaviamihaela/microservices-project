// Define package for this class
package com.programming.projectservice;

import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;

// Import necessary classes from the Spring Framework and Spring Boot
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Annotation - marks class as Spring Boot application, excludes specific auto-configuration classes
@SpringBootApplication
// Enable service to act as a client in a Eureka service discovery system
@EnableEurekaClient
@RequiredArgsConstructor
public class ProjectServiceApplication implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (projectRepository.count() < 1) {
            Project project = new Project();
            project.setName("iPhone 13");
            project.setDescription("iPhone 13");

            projectRepository.save(project);
        }
    }
}
