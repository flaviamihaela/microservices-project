package com.programming.projectservice.controller;

import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequest projectRequest) {
        projectService.createProject(projectRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

}