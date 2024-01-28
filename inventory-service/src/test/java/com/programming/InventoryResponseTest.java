// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;

import com.programming.inventoryservice.dto.InventoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class InventoryResponseTest {

    // Test to verify InventoryResponse DTO's constructor
    @Test
    void testInventoryResponseCreationAndAccessors() {
        // Creating InventoryResponse instance using Builder pattern with specified attributes
        String categoryCode = "category";
        String ideaDescription = "Java";
        String db = "MySQL";
        String backendStructure = "Microservices";
        String frontendStructure = "React";
        String mainComponents = "Component A, Component B";

        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .categoryCode(categoryCode)
                .ideaDescription(ideaDescription)
                .dB(db)
                .backendStructure(backendStructure)
                .frontendStructure(frontendStructure)
                .mainComponents(mainComponents)
                .build();

        // Asserting getters return correct values
        assertEquals(categoryCode, inventoryResponse.getCategoryCode());
        assertEquals(ideaDescription, inventoryResponse.getIdeaDescription());
        assertEquals(db, inventoryResponse.getDB());
        assertEquals(backendStructure, inventoryResponse.getBackendStructure());
        assertEquals(frontendStructure, inventoryResponse.getFrontendStructure());
        assertEquals(mainComponents, inventoryResponse.getMainComponents());
    }

    // Test to verify getters and setters
    @Test
    void testInventoryResponseSetters() {
        // Creating InventoryResponse instance using no-argument constructor
        InventoryResponse inventoryResponse = new InventoryResponse();

        // Setting attributes using setter methods
        String categoryCode = "category";
        String techStack = "Java";
        String db = "MySQL";
        String backendStructure = "Microservices";
        String frontendStructure = "React";
        String mainComponents = "Component A, Component B";

        inventoryResponse.setCategoryCode(categoryCode);
        inventoryResponse.setIdeaDescription(techStack);
        inventoryResponse.setDB(db);
        inventoryResponse.setBackendStructure(backendStructure);
        inventoryResponse.setFrontendStructure(frontendStructure);
        inventoryResponse.setMainComponents(mainComponents);

        // Asserting getters return values set by setters
        assertEquals(categoryCode, inventoryResponse.getCategoryCode(), "Category code should match the set value");
        assertEquals(techStack, inventoryResponse.getIdeaDescription(), "Tech stack should match the set value");
        assertEquals(db, inventoryResponse.getDB(), "Database should match the set value");
        assertEquals(backendStructure, inventoryResponse.getBackendStructure(), "Backend structure should match the set value");
        assertEquals(frontendStructure, inventoryResponse.getFrontendStructure(), "Frontend structure should match the set value");
        assertEquals(mainComponents, inventoryResponse.getMainComponents(), "Main components should match the set value");
    }

    // Test to verify the toString method includes all essential fields of InventoryResponse DTO
    @Test
    void testInventoryResponseToString() {
        // Creating InventoryResponse instance
        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();

        // Getting string representation of InventoryResponse
        String inventoryResponseString = inventoryResponse.toString();

        // Asserting string representation contains all necessary fields
        assertTrue(inventoryResponseString.contains("category"));
        assertTrue(inventoryResponseString.contains("Java"));
        assertTrue(inventoryResponseString.contains("MySQL"));
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();
        String notAnInventoryResponse = "Not an InventoryResponse";

        // Asserting that an InventoryResponse is not equal to null
        assertNotEquals(inventoryResponse, null);

        // Asserting that an InventoryResponse is not equal to an object of a different class
        assertNotEquals(inventoryResponse, notAnInventoryResponse);
    }

    // Test to verify the hashCode method
    @Test
    void testHashCodeMethod() {
        // Creating two InventoryResponse instances with identical attributes
        InventoryResponse inventoryResponse1 = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();
        InventoryResponse inventoryResponse2 = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();

        // Asserting two InventoryResponses with same data have same hashCode
        assertEquals(inventoryResponse1.hashCode(), inventoryResponse2.hashCode());
    }

    @Test
    void testInventoryResponseEquality() {
        // Creating two InventoryResponse instances with identical attributes
        InventoryResponse inventoryResponse1 = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();

        InventoryResponse inventoryResponse2 = InventoryResponse.builder()
                .categoryCode("category")
                .ideaDescription("Java")
                .dB("MySQL")
                .backendStructure("Microservices")
                .frontendStructure("React")
                .mainComponents("Component A, Component B")
                .build();

        // Asserting two InventoryResponses with the same attributes are considered equal
        assertEquals(inventoryResponse1, inventoryResponse2, "Two InventoryResponses with identical data should be equal");

        // Modifying one of the InventoryResponses
        inventoryResponse2.setIdeaDescription("Python");

        // Asserting two InventoryResponses with different attributes are not considered equal
        assertNotEquals(inventoryResponse1, inventoryResponse2, "Two InventoryResponses with different data should not be equal");
    }

}
