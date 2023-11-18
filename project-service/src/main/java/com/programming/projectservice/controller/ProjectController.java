// Define package for this class
package com.programming.projectservice.controller;

import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.service.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // defines class as REST Controller
@RequestMapping("/api/project") // defines base URL path for HTTP requests 
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // When POST request made to /api/project, this method will be called
    @PostMapping
    // HTTP status set to 'CREATED' when method completes successfully
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequest projectRequest) {
        // Call projectService to create new project, passing the ProjectRequest DTO from request body
        projectService.createProject(projectRequest);
    }

    // When GET request made to /api/project, this method will be called
    @GetMapping
    // HTTP status set to 'OK' when method completes successfully
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAllProjects() {
        // Call projectService to get all projects and return them
        return projectService.getAllProjects();
    }

}