package com.programming.projectservice.service;

import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void createProject(ProjectRequest projectRequest) {
        Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .price(projectRequest.getPrice())
                .build();

        projectRepository.save(project);
        log.info("Project {} is saved", project.getId());
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .price(project.getPrice())
                .build();
    }
}