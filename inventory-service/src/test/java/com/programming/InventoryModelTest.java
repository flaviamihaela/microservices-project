// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;
import com.programming.inventoryservice.model.Inventory;
import static org.junit.jupiter.api.Assertions.*;

class InventoryModelTest {

    // Test to verify Inventory model's constructor
    @Test
    void testInventoryCreationAndAccessors() {
        // Creating Inventory instance with specified attributes
        Long id = 1L;
        String categoryCode = "category";
        String idea= "Java";
        String db = "MySQL";
        String backendStructure = "Microservices";
        String frontendStructure = "React";
        String mainComponents = "Component A, Component B";

        Inventory inventory = new Inventory(id, categoryCode, idea, db, backendStructure, frontendStructure, mainComponents);

        // Asserting getters return correct values
        assertEquals(id, inventory.getId());
        assertEquals(categoryCode, inventory.getCategoryCode());
        assertEquals(idea, inventory.getIdeaDescription());
        assertEquals(db, inventory.getDB());
        assertEquals(backendStructure, inventory.getBackendStructure());
        assertEquals(frontendStructure, inventory.getFrontendStructure());
        assertEquals(mainComponents, inventory.getMainComponents());
    }

    // Test to verify set and get methods for each field in Inventory
    @Test
    void testSetAndGetMethods() {
        // Creating Inventory instance and setting new values
        Inventory inventory = new Inventory();
        inventory.setId(3L);
        inventory.setCategoryCode("newCategory");
        inventory.setIdeaDescription("Java");
        inventory.setDB("MySQL");
        inventory.setBackendStructure("Microservices");
        inventory.setFrontendStructure("React");
        inventory.setMainComponents("Component A, Component B");

        // Asserting each field was updated correctly
        assertEquals(3L, inventory.getId());
        assertEquals("newCategory", inventory.getCategoryCode());
        assertEquals("Java", inventory.getIdeaDescription());
        assertEquals("MySQL", inventory.getDB());
        assertEquals("Microservices", inventory.getBackendStructure());
        assertEquals("React", inventory.getFrontendStructure());
        assertEquals("Component A, Component B", inventory.getMainComponents());
    }


    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        Inventory inventory = new Inventory(1L, "category", "Java", "MySQL", "Microservices", "React", "Component A, Component B");
        String notAnInventory = "Not an inventory";

        // Asserting that an inventory is not equal to null
        assertNotEquals(inventory, null);

        // Asserting that an inventory is not equal to an object of a different class
        assertNotEquals(inventory, notAnInventory);
    }
}
