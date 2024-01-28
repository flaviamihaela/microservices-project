// Define package for class
package com.programming.projectservice;

// Import classes and annotations
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.projectservice.controller.ProjectController;
import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.dto.ProjectResponse;
import com.programming.projectservice.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

// Static imports for Mockito and MockMvc methods 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Use Mockito's JUnit extension to enable Mockito annotations 
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    // Mock ProjectService 
    @Mock
    private ProjectService projectService;

    // Create instance of ProjectController - inject mocked ProjectService into it
    @InjectMocks
    private ProjectController projectController;

    // Declare MockMvc to simulate HTTP requests in tests
    private MockMvc mockMvc;

    // Use ObjectMapper for converting objects to/from JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    // Before each test - set up MockMvc to work with injected ProjectController instance
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    // Test method to verify 'createProject' endpoint with a valid request 
    // Expect 201 status
    @Test
    void whenCreateProject_thenStatus201() throws Exception {
        // Prepare valid ProjectRequest object
        ProjectRequest projectRequest = getProjectRequest();
        
        // Configure mocked ProjectService to do nothing when 'createProject' is called
        doNothing().when(projectService).createProject(any(ProjectRequest.class));

        // Perform POST request to '/api/project' and verify response status is 201 Created
        mockMvc.perform(post("/api/project")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isCreated());

        // Verify service's 'createProject' method was called
        verify(projectService).createProject(any(ProjectRequest.class));
    }

    // Test method to verify 'getAllProjects' endpoint 
    // Expect 200 status
    @Test
    void whenGetAllProjects_thenStatus200() throws Exception {
        // Prepare list of ProjectResponse objects
        List<ProjectResponse> projectResponses = Arrays.asList(new ProjectResponse());
        
        // Configure mocked ProjectService to return prepared list when 'getAllProjects' is called
        when(projectService.getAllProjects()).thenReturn(projectResponses);

        // Perform GET request to '/api/project' 
        // Verify response status is 200 OK and content is as expected
        mockMvc.perform(get("/api/project")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(projectResponses)));

        // Verify that the service's 'getAllProjects' method was called
        verify(projectService).getAllProjects();
    }

    // Test method checks controller's response when ProjectRequest with empty strings sent to createProject endpoint
    // Expect 400 status
    @Test
    void whenCreateProjectWithInvalidData_thenStatus400() throws Exception {
        ProjectRequest projectRequest = new ProjectRequest("", "");
    
        // Perform POST request to the '/api/project' endpoint with invalid ProjectRequest object
        // Expect 400 status
        mockMvc.perform(post("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isBadRequest());
    
        // Verify createProject method of projectService is never called
        // Invalid request should be rejected before reaching the service layer
        verify(projectService, times(0)).createProject(any(ProjectRequest.class));
    }

    // Test method checks controller's response when ProjectRequest object sent without 'name' field
    // Expect 400 status
    @Test
    void whenCreateProjectWithMissingName_thenStatus400() throws Exception {
        ProjectRequest projectRequest = getProjectRequest();
        projectRequest.setName(null);
    
        // Perform POST request with the incomplete ProjectRequest object. 
        mockMvc.perform(post("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isBadRequest());
    }
    
    // Test method ensures controller returns 404 Not Found status code when POST request made to invalid path
    @Test
    void whenCreateProjectWithInvalidPath_thenStatus404() throws Exception {
        mockMvc.perform(post("/api/nonexistent")
                .contentType(MediaType.APPLICATION_JSON)
                // Send empty ProjectRequest - content irrelevant - path itself non-existent
                .content(objectMapper.writeValueAsString(new ProjectRequest())))
                .andExpect(status().isNotFound());
    }
    
    // Test method checks the controller's response when a PUT request is made to the '/api/project' endpoint
    // Expect 405 status - PUT method is not defined for this endpoint
    @Test
    void whenCreateProjectUsingGet_thenStatus405() throws Exception {
        mockMvc.perform(put("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProjectRequest())))
                .andExpect(status().isMethodNotAllowed());
    }

    // Helper method to create a ProjectRequest object with test data
    private ProjectRequest getProjectRequest() {
        return ProjectRequest.builder()
                .name("Project Name")
                .description("Project Description")
                .build();
    }
    
}
