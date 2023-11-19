// Define pacckage for class
package com.programming.inventoryservice.dto;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, equals, toString and hashCode methods
@Data
@AllArgsConstructor
@NoArgsConstructor
// Enables Builder pattern for object creation
@Builder

public class InventoryResponse {
    private String categoryCode;
    private boolean isInStock;
}
