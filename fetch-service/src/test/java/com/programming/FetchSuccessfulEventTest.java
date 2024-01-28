// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;

import com.programming.fetchservice.event.FetchSuccessfulEvent;

import static org.junit.jupiter.api.Assertions.*;

class FetchSuccessfulEventTest {

    // Test to verify FetchSuccessfulEvent's constructor
    @Test
    void testFetchSuccessfulEventCreationAndAccessors() {
        // Creating FetchSuccessfulEvent instance with specified attributes
        String fetchNumber = "FN123";

        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent(fetchNumber);

        // Asserting getters return correct values
        assertEquals(fetchNumber, fetchSuccessfulEvent.getFetchNumber());
    }

    // Test to verify set and get methods for the fetchNumber field
    @Test
    void testSetAndGetMethods() {
        // Creating FetchSuccessfulEvent instance and setting a new value
        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent();
        String newFetchNumber = "FN456";

        fetchSuccessfulEvent.setFetchNumber(newFetchNumber);

        // Asserting the field was updated correctly
        assertEquals(newFetchNumber, fetchSuccessfulEvent.getFetchNumber());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent("FN123");
        String notAFetchSuccessfulEvent = "Not a FetchSuccessfulEvent";

        // Asserting that a FetchSuccessfulEvent is not equal to null
        assertNotEquals(fetchSuccessfulEvent, null);

        // Asserting that a FetchSuccessfulEvent is not equal to an object of a different class
        assertNotEquals(fetchSuccessfulEvent, notAFetchSuccessfulEvent);
    }

    //Test for equals method
    @Test
    void testEquals() {
        FetchSuccessfulEvent event1 = new FetchSuccessfulEvent("fetch123");
        FetchSuccessfulEvent event2 = new FetchSuccessfulEvent("fetch123");
        FetchSuccessfulEvent event3 = new FetchSuccessfulEvent("fetch456");

        assertEquals(event1, event2, "Events with the same fetch number should be equal");
        assertNotEquals(event1, event3, "Events with different fetch numbers should not be equal");
    }

    //Test for hashcode method
    @Test
    void testHashCode() {
        FetchSuccessfulEvent event1 = new FetchSuccessfulEvent("fetch123");
        FetchSuccessfulEvent event2 = new FetchSuccessfulEvent("fetch123");

        assertEquals(event1.hashCode(), event2.hashCode(), "Hash codes should be equal for equal objects");
    }

    //Test for tostring method
    @Test
    void testToString() {
        FetchSuccessfulEvent event = new FetchSuccessfulEvent("fetch123");
        String expected = "FetchSuccessfulEvent(fetchNumber=fetch123)";

        assertEquals(expected, event.toString(), "toString should return the correct representation");
    }
}
