// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;

import com.programming.fetchservice.dto.InventoryResponse;

import static org.junit.jupiter.api.Assertions.*;

class FetchInventoryResponseTest {

    // Test to verify InventoryResponse DTO's constructor 
    @Test
    void testInventoryResponseCreationAndAccessors() {
        // Creating InventoryResponse instance with specified attributes
        String categoryCode = "category1";
        String techStack = "Java";
        String dB = "MySQL";
        String backendStructure = "Microservices";
        String frontendStructure = "React";
        String mainComponents = "Component A, Component B";

        InventoryResponse inventoryResponse = new InventoryResponse(categoryCode, techStack, dB, backendStructure, frontendStructure, mainComponents);

        // Asserting getters return correct values
        assertEquals(categoryCode, inventoryResponse.getCategoryCode());
        assertEquals(techStack, inventoryResponse.getIdeaDescription());
        assertEquals(dB, inventoryResponse.getDB());
        assertEquals(backendStructure, inventoryResponse.getBackendStructure());
        assertEquals(frontendStructure, inventoryResponse.getFrontendStructure());
        assertEquals(mainComponents, inventoryResponse.getMainComponents());
    }

    // Test to verify set and get methods for each field in InventoryResponse
    @Test
    void testSetAndGetMethods() {
        // Creating InventoryResponse instance and setting new values
        InventoryResponse inventoryResponse = new InventoryResponse();
        String newCategoryCode = "category2";
        String newIdea = "idea";
        String newDB = "PostgreSQL";
        String newBackendStructure = "Monolith";
        String newFrontendStructure = "Vue";
        String newMainComponents = "Component X, Component Y";

        inventoryResponse.setCategoryCode(newCategoryCode);
        inventoryResponse.setIdeaDescription(newIdea);
        inventoryResponse.setDB(newDB);
        inventoryResponse.setBackendStructure(newBackendStructure);
        inventoryResponse.setFrontendStructure(newFrontendStructure);
        inventoryResponse.setMainComponents(newMainComponents);

        // Asserting each field was updated correctly
        assertEquals(newCategoryCode, inventoryResponse.getCategoryCode());
        assertEquals(newIdea, inventoryResponse.getIdeaDescription());
        assertEquals(newDB, inventoryResponse.getDB());
        assertEquals(newBackendStructure, inventoryResponse.getBackendStructure());
        assertEquals(newFrontendStructure, inventoryResponse.getFrontendStructure());
        assertEquals(newMainComponents, inventoryResponse.getMainComponents());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        InventoryResponse inventoryResponse = new InventoryResponse("category1", "Java", "MySQL", "Microservices", "React", "Component A, Component B");
        String notAnInventoryResponse = "Not an InventoryResponse";

        // Asserting that an InventoryResponse is not equal to null
        assertNotEquals(inventoryResponse, null);

        // Asserting that an InventoryResponse is not equal to an object of a different class
        assertNotEquals(inventoryResponse, notAnInventoryResponse);
    }

    // Test for equals method
    @Test
    void testInventoryResponseEquals() {
        InventoryResponse inventoryResponse1 = new InventoryResponse("category1", "Java", "MySQL", "Microservices", "React", "Component A, Component B");
        InventoryResponse inventoryResponse2 = new InventoryResponse("category1", "Java", "MySQL", "Microservices", "React", "Component A, Component B");
        InventoryResponse inventoryResponse3 = new InventoryResponse("category2", "Python", "PostgreSQL", "Monolith", "Angular", "Component X, Component Y");

        // Test reflexivity
        assertEquals(inventoryResponse1, inventoryResponse1);

        // Test symmetry
        assertEquals(inventoryResponse1, inventoryResponse2);
        assertEquals(inventoryResponse2, inventoryResponse1);

        // Test transitivity
        assertNotEquals(inventoryResponse1, inventoryResponse3);
        assertNotEquals(inventoryResponse2, inventoryResponse3);

        // Test inequality
        assertNotEquals(inventoryResponse1, inventoryResponse3);
    }

    // Test for hashcode method
    @Test
    void testInventoryResponseHashCode() {
        InventoryResponse inventoryResponse1 = new InventoryResponse("category1", "Java", "MySQL", "Microservices", "React", "Component A, Component B");
        InventoryResponse inventoryResponse2 = new InventoryResponse("category1", "Java", "MySQL", "Microservices", "React", "Component A, Component B");

        // Test consistency
        assertEquals(inventoryResponse1.hashCode(), inventoryResponse1.hashCode());

        // Test equality
        assertEquals(inventoryResponse1.hashCode(), inventoryResponse2.hashCode());

        // Creating a new instance with different data
        InventoryResponse inventoryResponse3 = new InventoryResponse("category2", "Python", "PostgreSQL", "Monolith", "Angular", "Component X, Component Y");

        // Test non-equality
        assertNotEquals(inventoryResponse1.hashCode(), inventoryResponse3.hashCode());
    }

}
