//Define package for class
package com.programming;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FetchSuccessfulEventTest {

    // Test functionality of getters and setters
    @Test
    void testFetchSuccsessfulEventGettersAndSetters() {
        String fetchNumber = "1";

        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent();
        fetchSuccessfulEvent.setFetchNumber(fetchNumber);
        // Assert fetchNumber is correctly retrieved
        assertEquals(fetchNumber, fetchSuccessfulEvent.getFetchNumber(), "The getfetchNumber method should return the correct fetch number");
    }

    // Test the all-args constructor of FetchSuccessfulEvent
    @Test
    void testfetchSuccessfulEventAllArgsConstructor() {
        String fetchNumber = "1";

        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent(fetchNumber);

        assertEquals(fetchNumber, fetchSuccessfulEvent.getFetchNumber(), "Constructor should set the correct fetch number");
    }

    // Test equals and hashCode methods of FetchSuccessfulEvent
    @Test
    void testfetchSuccessfulEventEqualsAndHashCode() {
        // Creating two identical and one different event instances
        FetchSuccessfulEvent fetchSuccessfulEvent1 = new FetchSuccessfulEvent("1");
        FetchSuccessfulEvent fetchSuccessfulEvent2 = new FetchSuccessfulEvent("1");
        FetchSuccessfulEvent fetchSuccessfulEvent3 = new FetchSuccessfulEvent("3");
        // Asserting equality and inequality
        assertEquals(fetchSuccessfulEvent1, fetchSuccessfulEvent2, "Two project requests with the same data should be equal");
        assertNotEquals(fetchSuccessfulEvent1, fetchSuccessfulEvent3, "Two project requests with different data should not be equal");
        // Asserting hash code consistency with equals
        assertEquals(fetchSuccessfulEvent1.hashCode(), fetchSuccessfulEvent2.hashCode(), "Hash codes should be equal for equal objects");
        assertNotEquals(fetchSuccessfulEvent1.hashCode(), fetchSuccessfulEvent3.hashCode(), "Hash codes should not be equal for different objects");
    }

    // Test the toString method of FetchSuccessfulEvent
    @Test
    void testFetchSuccessfulEventToString() {
        FetchSuccessfulEvent fetchSuccessfulEvent = new FetchSuccessfulEvent("1");
        String fetchSuccessfulEventString = fetchSuccessfulEvent.toString();
        // Assert toString contains fetchNumber
        assertTrue(fetchSuccessfulEventString.contains("1"), "toString should include fetchNumber");
    }
}
