// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;
import com.programming.fetchservice.model.Fetch;
import com.programming.fetchservice.model.FetchIdeas;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FetchModelTest {

    // Test to verify Fetch model's constructor
    @Test
    void testFetchCreationAndAccessors() {
        // Creating Fetch instance with specified attributes
        Long id = 1L;
        String fetchNumber = "Fetch001";
        List<FetchIdeas> fetchIdeasList = List.of(new FetchIdeas());

        Fetch fetch = new Fetch(id, fetchNumber, fetchIdeasList);

        // Asserting getters return correct values
        assertEquals(id, fetch.getId());
        assertEquals(fetchNumber, fetch.getFetchNumber());
        assertEquals(fetchIdeasList, fetch.getFetchIdeasList());
    }

    // Test to verify set and get methods for each field in Fetch
    @Test
    void testSetAndGetMethods() {
        // Creating Fetch instance and setting new values
        Fetch fetch = new Fetch();
        Long newId = 2L;
        String newFetchNumber = "Fetch002";
        List<FetchIdeas> newFetchIdeasList = List.of(new FetchIdeas());

        fetch.setId(newId);
        fetch.setFetchNumber(newFetchNumber);
        fetch.setFetchIdeasList(newFetchIdeasList);

        // Asserting each field was updated correctly
        assertEquals(newId, fetch.getId());
        assertEquals(newFetchNumber, fetch.getFetchNumber());
        assertEquals(newFetchIdeasList, fetch.getFetchIdeasList());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        Fetch fetch = new Fetch(1L, "Fetch001", List.of(new FetchIdeas(/* attributes here */)));
        String notAFetch = "Not a Fetch";

        // Asserting that a Fetch is not equal to null
        assertNotEquals(fetch, null);

        // Asserting that a Fetch is not equal to an object of a different class
        assertNotEquals(fetch, notAFetch);
    }
}
