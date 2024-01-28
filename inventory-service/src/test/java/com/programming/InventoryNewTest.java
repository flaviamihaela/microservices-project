//Define package for class
package com.programming;

//Imports
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.programming.inventoryservice.dto.InventoryNew;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InventoryNew.class)
class InventoryNewTest {

    // Test to verify InventoryNew constructor
    @Test
    void testInventoryNewCreationAndAccessors() {
        String categoryCode = "category123";
        String ideaDescription = "New Inventory Idea";
        String dB = "MySQL";
        String backendStructure = "Microservices";
        String frontendStructure = "ReactJS";
        String mainComponents = "Authentication, Dashboard";

        InventoryNew inventoryNew = InventoryNew.builder()
                .categoryCode(categoryCode)
                .ideaDescription(ideaDescription)
                .dB(dB)
                .backendStructure(backendStructure)
                .frontendStructure(frontendStructure)
                .mainComponents(mainComponents)
                .build();

        assertAll("Should correctly set and retrieve all properties",
            () -> assertEquals(categoryCode, inventoryNew.getCategoryCode(), "Checking if categoryCode matches"),
            () -> assertEquals(ideaDescription, inventoryNew.getIdeaDescription(), "Checking if ideaDescription matches"),
            () -> assertEquals(dB, inventoryNew.getDB(), "Checking if dB matches"),
            () -> assertEquals(backendStructure, inventoryNew.getBackendStructure(), "Checking if backendStructure matches"),
            () -> assertEquals(frontendStructure, inventoryNew.getFrontendStructure(), "Checking if frontendStructure matches"),
            () -> assertEquals(mainComponents, inventoryNew.getMainComponents(), "Checking if mainComponents matches")
        );
    }

    // Test to verify InventoryNew's toString method
    @Test
    void testInventoryNewToString() {
        InventoryNew inventoryNew = InventoryNew.builder()
                .categoryCode("catCode")
                .ideaDescription("Description")
                .dB("Database")
                .backendStructure("Backend")
                .frontendStructure("Frontend")
                .mainComponents("Components")
                .build();

        String inventoryNewString = inventoryNew.toString();

        assertAll("Should include all fields in toString representation",
            () -> assertTrue(inventoryNewString.contains("catCode"), "toString should include categoryCode"),
            () -> assertTrue(inventoryNewString.contains("Description"), "toString should include ideaDescription"),
            () -> assertTrue(inventoryNewString.contains("Database"), "toString should include dB"),
            () -> assertTrue(inventoryNewString.contains("Backend"), "toString should include backendStructure"),
            () -> assertTrue(inventoryNewString.contains("Frontend"), "toString should include frontendStructure"),
            () -> assertTrue(inventoryNewString.contains("Components"), "toString should include mainComponents")
        );
    }

    // Test to verify equals method
    @Test
    void testEqualsMethod() {
        InventoryNew inventoryNew1 = new InventoryNew("code", "Idea", "DB", "Backend", "Frontend", "Components");
        InventoryNew inventoryNew2 = new InventoryNew("code", "Idea", "DB", "Backend", "Frontend", "Components");

        assertEquals(inventoryNew1, inventoryNew2, "Two InventoryNew objects with the same data should be considered equal");

        InventoryNew inventoryNewDifferent = new InventoryNew("diffCode", "Different Idea", "Different DB", "Different Backend", "Different Frontend", "Different Components");
        assertNotEquals(inventoryNew1, inventoryNewDifferent, "InventoryNew objects with different data should not be considered equal");
    }

    // Test to verify the hashCode method
    @Test
    void testHashCodeMethod() {

        InventoryNew inventoryNew1 = new InventoryNew("code", "Idea", "DB", "Backend", "Frontend", "Components");
        InventoryNew inventoryNew2 = new InventoryNew("code", "Idea", "DB", "Backend", "Frontend", "Components");

        assertEquals(inventoryNew1.hashCode(), inventoryNew2.hashCode(), "hashCode should be consistent for equal InventoryNew objects");
    }

    // Test equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        InventoryNew inventoryNew = new InventoryNew("code1", "Idea1", "DB1", "Backend1", "Frontend1", "Components1");
        String notAnInventory = "Not an Inventory";

        assertNotEquals(inventoryNew, null, "An InventoryNew object should not be equal to null");
        assertNotEquals(inventoryNew, notAnInventory, "An InventoryNew object should not be equal to an object of a different type");
    }
}
