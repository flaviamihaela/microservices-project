
// Define package for this class
package com.programming.projectservice.service;

// Import statements for required classes and annotations
import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;

import lombok.RequiredArgsConstructor; 
import lombok.extern.slf4j.Slf4j; 
import org.springframework.stereotype.Service; 

import java.util.List;

// Declare class as Service
@Service
// Use Lombok to inject dependencies and for logging capability
@RequiredArgsConstructor
@Slf4j

public class ProjectService {

    // Inject the ProjectRepository - via Lombok's @RequiredArgsConstructor
    private final ProjectRepository projectRepository;

    // Method to create a project
    public void createProject(ProjectRequest projectRequest) {

        // Map DTO to the Project entity
        Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .build();

        // Save Project entity to db using ProjectRepository
        projectRepository.save(project);

        log.info("Project {} is saved", project.getId());
    }

    // Method to retrieve all projects as a list of ProjectResponse DTOs
    public List<ProjectResponse> getAllProjects() {
        // Retrieve all Project entities from db using ProjectRepository
        List<Project> projects = projectRepository.findAll();

        // Convert List of Project entities to List of ProjectResponse DTOs using mapToProjectResponse method
        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    // Helper method to map a Project entity to a ProjectResponse DTO
    private ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }
}