//Package for test class
package com.programming;

import com.programming.inventoryservice.InventoryServiceApplication;
import com.programming.inventoryservice.dto.InventoryNew;
// Import classes
import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.model.Inventory;
import com.programming.inventoryservice.repository.InventoryRepository;
import com.programming.inventoryservice.service.InventoryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

// Static imports for Mockito and JUnit methods
import static org.mockito.Mockito.*;

// Enable Mockito annotations in test class
@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    // Create mock instance of InventoryRepository
    @Mock
    private InventoryRepository inventoryRepository;

    // Inject above mock into InventoryService instance
    @InjectMocks
    private InventoryService inventoryService;

    // Test case to simulate application's main method execution
    @Test
    public void main() {
        // Arguments array typically passed to the main method; empty here as it's not used
        String[] args = {};
        // Call main method to initiate the application
        InventoryServiceApplication.main(args);
    }

    // Test isInStock method when inventory exists
    @Test
    public void testIsInStockWithFoundInventory() {
        // Setup mock inventory
        String categoryCode = "testCode";
        Inventory mockInventory = Inventory.builder()
                .categoryCode(categoryCode)
                .ideaDescription("Test Idea")
                .build();
        when(inventoryRepository.findFirstByCategoryCode(categoryCode)).thenReturn(mockInventory);

        // Call method and assert conditions
        List<InventoryResponse> responses = inventoryService.isInStock(Arrays.asList(categoryCode));

        verify(inventoryRepository, times(1)).findFirstByCategoryCode(categoryCode);
        // Ensure the item is deleted as part of the method's logic
        verify(inventoryRepository, times(1)).delete(mockInventory);
        // Response should not be empty, should contain the correct categoryCode
        assert !responses.isEmpty();
        assert responses.get(0).getCategoryCode().equals(categoryCode);
    }

    // Test isInStock method when no inventory is found
    @Test
    public void testIsInStockWithNoInventoryFound() {
        // Setup non-existing inventory code
        String categoryCode = "nonExistingCode";
        when(inventoryRepository.findFirstByCategoryCode(categoryCode)).thenReturn(null);

        // Call method and assert no inventory is found
        List<InventoryResponse> responses = inventoryService.isInStock(Arrays.asList(categoryCode));

        // Verify method call and expect empty response
        verify(inventoryRepository, times(1)).findFirstByCategoryCode(categoryCode);
        assert responses.isEmpty();
    }

     // Test addIdea method for adding new inventory
    @Test
    public void testAddIdea() {
        // Setup new inventory object
        InventoryNew inventoryNew = new InventoryNew();

        // Mock saving behavior
        Inventory savedInventory = Inventory.builder()
                .categoryCode(inventoryNew.getCategoryCode())
                .build();

        // Mock saved inventory
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(savedInventory);

        // Call addIdea and verify behavior
        inventoryService.addIdea(inventoryNew);

        // Verify inventory is saved once
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

}
