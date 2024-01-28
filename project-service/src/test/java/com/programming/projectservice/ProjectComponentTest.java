//Package for test class
package com.programming.projectservice;

//Imports
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.projectservice.controller.ProjectController;
import com.programming.projectservice.dto.ProjectRequest;
import com.programming.projectservice.model.Project;
import com.programming.projectservice.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ActiveProfiles;

//Adds Spring TestContext Framework into JUnit 5
@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = ProjectServiceApplication.class)
public class ProjectComponentTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    // Main entry point for server-side Spring MVC test support
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
         // Initialize MockMvc with web application context
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Clears project repository before each test to ensure each test starts with a clean state
        projectRepository.deleteAll();
    }


    // Test case to simulate application's main method execution
    @Test
    public void main() {
        // Arguments array typically passed to the main method; empty here as it's not used
        String[] args = {};
        // Call main method to initiate the application
        ProjectServiceApplication.main(args);
    }
    
    // Test case for creating a project
    @Test
    public void whenPostProject_thenCreateProject() throws Exception {
        ProjectRequest projectRequest = new ProjectRequest("Integration Test Project", "Description for Integration Test Project");

        // Perform POST request to the /api/project endpoint.
        mockMvc.perform(post("/api/project")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isCreated());
    }

    //Test to ensure GET request works (OK status) and returns correct project data.
    @Test
    public void whenGetProjects_thenStatusOk() throws Exception {
        Project sampleProject = new Project(null, "Sample Project", "Sample Description");
        projectRepository.save(sampleProject);

        mockMvc.perform(get("/api/project"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Sample Project"))
            .andExpect(jsonPath("$[0].description").value("Sample Description"));
    }

    // Test to ensure that an invalid project request is handled correctly
    @Test
    public void whenPostInvalidProject_thenBadRequest() throws Exception {
        // Create an invalid project request (e.g., missing description)
        ProjectRequest invalidProjectRequest = new ProjectRequest("Invalid Project", null);
    
        // Perform POST request to the /api/project endpoint with invalid request
        mockMvc.perform(post("/api/project")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(invalidProjectRequest)))
                .andExpect(status().isBadRequest()); // Expecting HTTP 400 Bad Request
        }
}
