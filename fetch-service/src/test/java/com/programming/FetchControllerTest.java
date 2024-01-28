// Package for test class
package com.programming;

// Import classes and annotations
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.fetchservice.controller.FetchController;
import com.programming.fetchservice.dto.ChatResponse;
import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.service.FetchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Use Mockito's JUnit extension to enable Mockito annotations
@ExtendWith(MockitoExtension.class)
class FetchControllerTest {

    // Mock FetchService
    @Mock
    private FetchService fetchService;

    // Mock RestTemplate
    @Mock
    private RestTemplate restTemplate;

    // Create instance of FetchController - inject mocked FetchService and RestTemplate into it
    @InjectMocks
    private FetchController fetchController;

    // Declare MockMvc to simulate HTTP requests in tests
    private MockMvc mockMvc;

    // Use ObjectMapper for converting objects to/from JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    // Before each test - set up MockMvc to work with injected FetchController instance
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(fetchController).build();
    }

    // Test method for 'placeFetch' POST endpoint
    @Test
    void whenPlaceFetch_thenStatus201() throws Exception {
        // Prepare valid FetchRequest object
        FetchRequest fetchRequest = new FetchRequest(); // Populate with necessary fields

        // Perform POST request to '/api/fetch' and verify response status is 201 Created
        mockMvc.perform(post("/api/fetch")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fetchRequest)))
                .andExpect(status().isCreated());

        // Verify service's 'placeFetch' method was called
        verify(fetchService).placeFetch(any(FetchRequest.class));
    }

    // Test method for the 'chat' GET endpoint
    @Test
    void whenChat_thenStatus200() throws Exception {
        String prompt = "Hello";

        // Perform GET request to '/api/fetch' with query parameter
        mockMvc.perform(get("/api/fetch")
                .param("prompt", prompt)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
