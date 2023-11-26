// Define package for class
package com.programming.fetchservice.dto;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, equals, hashCode and toString methods
@Data
@AllArgsConstructor
@NoArgsConstructor
// Enables the Builder pattern for object creation
@Builder

public class InventoryResponse {
    private String categoryCode;
    private boolean isInStock;
    private String techStack;
    private String dB;
    private String backendStructure;
    private String frontendStructure;
    private String mainComponents;
}
