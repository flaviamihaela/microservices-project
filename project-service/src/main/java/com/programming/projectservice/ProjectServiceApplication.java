// Define package for class
package com.programming.projectservice;

// Import classes and annotations
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Defines class as main entry point for Spring Boot Application
@SpringBootApplication
// Enables service to register with a Eureka service discovery system
@EnableEurekaClient
// Automatically generates a constructor with required arguments
@RequiredArgsConstructor
public class ProjectServiceApplication implements CommandLineRunner {

    // Dependency Injection
    private final ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectServiceApplication.class, args);
    }

     // Overrides run method from CommandLineRunner interface
    @Override
    public void run(String... args) {
        // Check if no projects in repository
        if (projectRepository.count() < 1) {
            Project project = new Project();
            project.setName("WA");
            project.setDescription("Web Apps");

            // Saves new project object to repository
            projectRepository.save(project);
        }
    }
}
