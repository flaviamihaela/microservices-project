//Package for test class
package com.programming.projectservice;

//Import classes 
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;
import com.programming.projectservice.service.ProjectService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

// Static imports for Mockito and JUnit methods 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// Enable Mockito annotations in test class
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    // Create mock instance of ProjectRepository
    @Mock
    private ProjectRepository projectRepository;

    // Inject above mock into ProjectService instance 
    @InjectMocks
    private ProjectService projectService;
    // Test case for verifying service correctly returns all projects
    @Test
    void whenGetAllProjects_thenAllProjectsAreReturned() {
        // Prepare list of project entities as simulated response from repository
        List<Project> projects = List.of(
            new Project("id1", "Project 1", "Description 1"),
            new Project("id2", "Project 2", "Description 2")
        );

        // Configure mock to return prepared list when findAll is called
        when(projectRepository.findAll()).thenReturn(projects);

        // Call service method and capture its response
        List<ProjectResponse> projectResponses = projectService.getAllProjects();

        // Assert response has correct size and contains projects with expected names
        assertEquals(2, projectResponses.size());
        assertEquals("Project 1", projectResponses.get(0).getName());
        assertEquals("Project 2", projectResponses.get(1).getName());
    }

    // Test case for verifying empty list returned when no projects
    @Test
    void whenNoProjects_thenGetAllProjectsReturnsEmptyList() {
        // Configure mock to return empty list when findAll called
        when(projectRepository.findAll()).thenReturn(List.of());

        // Call service method and capture its response
        List<ProjectResponse> projectResponses = projectService.getAllProjects();

        // Assert service returns empty list when no projects
        assertTrue(projectResponses.isEmpty());
    }

}
