// Define package for class
package com.programming.inventoryservice.service;

// Import classes and annotations
import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// Declare class as Spring service component
@Service
// Automatically generates a constructor with required arguments
@RequiredArgsConstructor
// Enables logging
@Slf4j
public class InventoryService {

    // Dependency injection 
    private final InventoryRepository inventoryRepository;
    // Ensures method is used within transactional context 
    @Transactional(readOnly = true)
    // Throws checked exceptions without actually declaring this in the method's throw clause
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> categoryCode) {
        log.info("Checking Inventory");

        // Fetches inventory items by category codes and maps them to InventoryResponse objects 
        return inventoryRepository.findByCategoryCodeIn(categoryCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                        .categoryCode(inventory.getCategoryCode())
                        .techStack(inventory.getTechStack())
                        .dB(inventory.getDB())
                        .backendStructure(inventory.getBackendStructure())
                        .frontendStructure(inventory.getFrontendStructure())
                        .mainComponents(inventory.getMainComponents())
                        .build()
                ).toList();
    }
}