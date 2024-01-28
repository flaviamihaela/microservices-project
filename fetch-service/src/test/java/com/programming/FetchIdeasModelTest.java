// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;

import com.programming.fetchservice.model.FetchIdeas;

import static org.junit.jupiter.api.Assertions.*;

class FetchIdeasTest {

    // Test to verify FetchIdeas entity's constructor
    @Test
    void testFetchIdeasCreationAndAccessors() {
        // Creating FetchIdeas instance with specified attributes
        Long id = 1L;
        String categoryCode = "category1";
        Integer quantity = 100;

        FetchIdeas fetchIdeas = new FetchIdeas(id, categoryCode, quantity);

        // Asserting getters return correct values
        assertEquals(id, fetchIdeas.getId());
        assertEquals(categoryCode, fetchIdeas.getCategoryCode());
        assertEquals(quantity, fetchIdeas.getQuantity());
    }

    // Test to verify set and get methods for each field in FetchIdeas
    @Test
    void testSetAndGetMethods() {
        // Creating FetchIdeas instance and setting new values
        FetchIdeas fetchIdeas = new FetchIdeas();
        Long newId = 2L;
        String newCategoryCode = "category2";
        Integer newQuantity = 200;

        fetchIdeas.setId(newId);
        fetchIdeas.setCategoryCode(newCategoryCode);
        fetchIdeas.setQuantity(newQuantity);

        // Asserting each field was updated correctly
        assertEquals(newId, fetchIdeas.getId());
        assertEquals(newCategoryCode, fetchIdeas.getCategoryCode());
        assertEquals(newQuantity, fetchIdeas.getQuantity());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        FetchIdeas fetchIdeas = new FetchIdeas(1L, "category1", 100);
        String notAFetchIdeas = "Not a FetchIdeas";

        // Asserting that FetchIdeas is not equal to null
        assertNotEquals(fetchIdeas, null);

        // Asserting that FetchIdeas is not equal to an object of a different class
        assertNotEquals(fetchIdeas, notAFetchIdeas);
    }
}
