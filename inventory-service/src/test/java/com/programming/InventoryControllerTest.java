// Define package for class
package com.programming;

// Import classes and annotations
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.inventoryservice.controller.InventoryController;
import com.programming.inventoryservice.dto.InventoryNew;
import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.service.InventoryService;
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

import static org.mockito.ArgumentMatchers.anyList;
// Static imports for Mockito and MockMvc methods
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Use Mockito's JUnit extension to enable Mockito annotations
@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    // Mock InventoryService
    @Mock
    private InventoryService inventoryService;

    // Create instance of InventoryController - inject mocked InventoryService into it
    @InjectMocks
    private InventoryController inventoryController;

    // Declare MockMvc to simulate HTTP requests in tests
    private MockMvc mockMvc;

    // Use ObjectMapper for converting objects to/from JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    // Before each test - set up MockMvc to work with injected InventoryController instance
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
    }

    // Test method to verify 'isInStock' endpoint with valid request
    // Expect 200 status
    @Test
    void whenIsInStock_thenStatus200() throws Exception {
        // Prepare list of InventoryResponse objects
        List<InventoryResponse> inventoryResponses = Arrays.asList(new InventoryResponse());
        
        // Configure mocked InventoryService to return prepared list when 'isInStock' is called
        when(inventoryService.isInStock(anyList())).thenReturn(inventoryResponses);

        // Perform GET request to '/api/inventory' and verify response status is 200 OK and content is as expected
        mockMvc.perform(get("/api/inventory")
                .param("categoryCode", "category1", "category2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(inventoryResponses)));

        // Verify that the service's 'isInStock' method was called
        verify(inventoryService).isInStock(anyList());
    }

    // Test method to verify 'isInStock' endpoint with invalid request (e.g., no parameters)
    // Expect 400 status
    @Test
    void whenIsInStockWithInvalidData_thenStatus400() throws Exception {
        // Perform GET request to the '/api/inventory' endpoint without required parameters
        mockMvc.perform(get("/api/inventory")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify 'isInStock' method of inventoryService is never called
        // Invalid request should be rejected before reaching the service layer
        verify(inventoryService, times(0)).isInStock(anyList());
    }


    // Test method ensures controller returns 404 Not Found status code when GET request made to invalid path
    @Test
    void whenGetRequestToInvalidPath_thenStatus404() throws Exception {
        mockMvc.perform(get("/api/nonexistent")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Test method checks the controller's response when a PUT request is made to the '/api/inventory' endpoint
    // Expect 405 status - PUT method is not defined for this endpoint
    @Test
    void whenPostRequestToInventory_thenStatus405() throws Exception {
        mockMvc.perform(put("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList("category1", "category2"))))
                .andExpect(status().isMethodNotAllowed());
    }

    // Test method to verify behavior of 'addIdea' endpoint when a POST request is made to the '/api/inventory' endpoint 
    // Expevct 201 Created status code, indicating inventory idea has been successfully added
    @Test
    void addIdeaTest() throws Exception {
        // Creates new InventoryNew object to simulate the inventory data being sent in the request
        InventoryNew inventoryNew = new InventoryNew();

        // Perform a POST request to '/api/inventory' with the inventory data
        // Expect the server to respond with a 201 Created status code
        mockMvc.perform(post("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)  
                .content(objectMapper.writeValueAsString(inventoryNew)))  
                .andExpect(status().isCreated()); 

        // Verify that the 'addIdea' method in the InventoryService is called exactly once with the provided inventory data
        verify(inventoryService, times(1)).addIdea(inventoryNew);
    }

}
