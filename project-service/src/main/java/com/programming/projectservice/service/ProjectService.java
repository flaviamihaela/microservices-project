
// Define package for class
package com.programming.projectservice.service;

// Import classes and annotations
import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;

import lombok.RequiredArgsConstructor; 
import lombok.extern.slf4j.Slf4j; 
import org.springframework.stereotype.Service; 

import java.util.List;

// Declare class as Spring Service component
@Service
// Automatically generates a constructor with required arguments
@RequiredArgsConstructor
// Confers logging capability
@Slf4j

public class ProjectService {

    // Dependency injection
    private final ProjectRepository projectRepository;

    // Method to create a project
    public void createProject(ProjectRequest projectRequest) {

        // Maps DTO to the Project entity
        Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .build();

        // Saves Project entity to db using ProjectRepository
        projectRepository.save(project);

        log.info("Project {} is saved", project.getId());
    }

    // Method to retrieve all projects as list of ProjectResponse DTOs
    public List<ProjectResponse> getAllProjects() {
        // Retrieve all Project entities from db using ProjectRepository
        List<Project> projects = projectRepository.findAll();

        // Convert list of Project entities to list of ProjectResponse DTOs using mapToProjectResponse method
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