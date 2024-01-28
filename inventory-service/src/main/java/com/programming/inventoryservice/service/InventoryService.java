// Define package for class
package com.programming.inventoryservice.service;

// Import classes and annotations
import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.model.Inventory;
import com.programming.inventoryservice.repository.InventoryRepository;
import com.programming.inventoryservice.dto.InventoryNew;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional
    // Throws checked exceptions without actually declaring this in the method's throw clause
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> categoryCode) {
        log.info("Checking Inventory");
        List<InventoryResponse> responses = new ArrayList<>();

        for (String code : categoryCode) {
            // Fetches the first item found for each category code
            Inventory inventory = inventoryRepository.findFirstByCategoryCode(code);

            if (inventory != null) {
                responses.add(
                    InventoryResponse.builder()
                        .categoryCode(inventory.getCategoryCode())
                        .ideaDescription(inventory.getIdeaDescription())
                        .dB(inventory.getDB())
                        .backendStructure(inventory.getBackendStructure())
                        .frontendStructure(inventory.getFrontendStructure())
                        .mainComponents(inventory.getMainComponents())
                        .build()
                );

                // Deletes the fetched item from the database
                inventoryRepository.delete(inventory);
            }
        }

        // Returns the response list
        return responses;
    }

    public void addIdea (InventoryNew inventoryNew){
        // Maps DTO to the Inventory entity
        Inventory inventoryIdea = Inventory.builder()
                .categoryCode(inventoryNew.getCategoryCode())
                .ideaDescription(inventoryNew.getIdeaDescription())
                .dB(inventoryNew.getDB())
                .backendStructure(inventoryNew.getBackendStructure())
                .frontendStructure(inventoryNew.getFrontendStructure())
                .mainComponents(inventoryNew.getMainComponents())
                .build();

        // Saves Project entity to db using ProjectRepository
        inventoryRepository.save(inventoryIdea);

        log.info("New idea {} is saved", inventoryIdea.getId());
    }
}