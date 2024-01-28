// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;
import com.programming.fetchservice.dto.FetchIdeasDto;
import com.programming.fetchservice.dto.FetchRequest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FetchRequestTest {

    // Test to verify FetchRequest DTO's constructor
    @Test
    void testFetchRequestCreationAndAccessors() {
        // Creating FetchRequest instance with specified attributes
        List<FetchIdeasDto> fetchIdeasDtoList = List.of(new FetchIdeasDto());

        FetchRequest fetchRequest = new FetchRequest(fetchIdeasDtoList);

        // Asserting getters return correct values
        assertEquals(fetchIdeasDtoList, fetchRequest.getFetchIdeasDtoList());
    }

    // Test to verify set and get methods for each field in FetchRequest
    @Test
    void testSetAndGetMethods() {
        // Creating FetchRequest instance and setting new values
        FetchRequest fetchRequest = new FetchRequest();
        List<FetchIdeasDto> newFetchIdeasDtoList = List.of(new FetchIdeasDto());
        fetchRequest.setFetchIdeasDtoList(newFetchIdeasDtoList);

        // Asserting field was updated correctly
        assertEquals(newFetchIdeasDtoList, fetchRequest.getFetchIdeasDtoList());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        FetchRequest fetchRequest = new FetchRequest(List.of(new FetchIdeasDto()));
        String notAFetchRequest = "Not a FetchRequest";

        // Asserting that a FetchRequest is not equal to null
        assertNotEquals(fetchRequest, null);

        // Asserting that a FetchRequest is not equal to an object of a different class
        assertNotEquals(fetchRequest, notAFetchRequest);
    }

    // Test the equals and hashCode methods
    @Test
    void testEqualsAndHashCode() {
        FetchRequest fetchRequest1 = new FetchRequest(List.of(new FetchIdeasDto(1l,"Idea1")));
        FetchRequest fetchRequest2 = new FetchRequest(List.of(new FetchIdeasDto(1l,"Idea1")));
        FetchRequest fetchRequest3 = new FetchRequest(List.of(new FetchIdeasDto(3l,"Idea2")));
    
        // Test reflexivity
        assertTrue(fetchRequest1.equals(fetchRequest1));
    
        // Test symmetry
        assertTrue(fetchRequest1.equals(fetchRequest2) && fetchRequest2.equals(fetchRequest1));
    
        // Test transitivity
        assertFalse(fetchRequest1.equals(fetchRequest2) && fetchRequest2.equals(fetchRequest3) && fetchRequest1.equals(fetchRequest3));
    
        // Test consistency
        assertTrue(fetchRequest1.equals(fetchRequest2));
        assertTrue(fetchRequest1.equals(fetchRequest2));
    
        // Test equals consistency with hashCode
         assertEquals(fetchRequest1.hashCode(), fetchRequest2.hashCode());
    
        // Test unequal objects have different hashCode
        assertNotEquals(fetchRequest1.hashCode(), fetchRequest3.hashCode());
    }
}
