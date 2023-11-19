// Define package for class
package com.programming.projectservice.controller;

// Import classes and annotations
import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.service.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Defines class as REST Controller
@RestController
// Base URL path for all request mappings in this controller
@RequestMapping("/api/project")
// Generates a constructor with required arguments
@RequiredArgsConstructor
public class ProjectController {

    // Dependency injection
    private final ProjectService projectService;

    // Method to handle POST requests to /api/project
    @PostMapping
    // Sets the HTTP status code to 'CREATED' (201) when this method completes successfully
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequest projectRequest) {
        // Delegates to the projectService to create a new project using the provided request data
        projectService.createProject(projectRequest);
    }

    // Method to handle GET requests to /api/project
    @GetMapping
    // Sets the HTTP status code to 'OK' (200) when this method completes successfully
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAllProjects() {
        // Calls projectService to get all projects and return list
        return projectService.getAllProjects();
    }

}