// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;
import com.programming.fetchservice.dto.FetchIdeasDto;
import static org.junit.jupiter.api.Assertions.*;

class FetchIdeasDtoTest {

    // Test to verify FetchIdeasDto's constructor
    @Test
    void testFetchIdeasDtoCreationAndAccessors() {
        // Creating FetchIdeasDto instance with specified attributes
        Long id = 1L;
        String categoryCode = "category1";

        FetchIdeasDto fetchIdeasDto = new FetchIdeasDto(id, categoryCode);

        // Asserting getters return correct values
        assertEquals(id, fetchIdeasDto.getId());
        assertEquals(categoryCode, fetchIdeasDto.getCategoryCode());
    }

    // Test to verify set and get methods for each field in FetchIdeasDto
    @Test
    void testSetAndGetMethods() {
        // Creating FetchIdeasDto instance and setting new values
        FetchIdeasDto fetchIdeasDto = new FetchIdeasDto();
        Long newId = 2L;
        String newCategoryCode = "category2";

        fetchIdeasDto.setId(newId);
        fetchIdeasDto.setCategoryCode(newCategoryCode);

        // Asserting each field was updated correctly
        assertEquals(newId, fetchIdeasDto.getId());
        assertEquals(newCategoryCode, fetchIdeasDto.getCategoryCode());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        FetchIdeasDto fetchIdeasDto = new FetchIdeasDto(1L, "category1");
        String notAFetchIdeasDto = "Not a FetchIdeasDto";

        // Asserting that a FetchIdeasDto is not equal to null
        assertNotEquals(fetchIdeasDto, null);

        // Asserting that a FetchIdeasDto is not equal to an object of a different class
        assertNotEquals(fetchIdeasDto, notAFetchIdeasDto);
    }

        // Test for verifying the equals method
        @Test
        void testEqualsMethod() {
            FetchIdeasDto dto1 = new FetchIdeasDto(1L, "category1");
            FetchIdeasDto dto2 = new FetchIdeasDto(1L, "category1");
            FetchIdeasDto dto3 = new FetchIdeasDto(2L, "category2");
    
            // Reflexivity
            assertEquals(dto1, dto1);
    
            // Symmetry
            assertEquals(dto1, dto2);
            assertEquals(dto2, dto1);
    
            // Consistency
            assertEquals(dto1, dto2);
            assertEquals(dto1, dto2);
    
            // Null comparison
            assertNotEquals(dto1, null);
    
            // Different objects
            assertNotEquals(dto1, dto3);
        }
    
        // Test for verifying the hashCode method
        @Test
        void testHashCodeMethod() {
            FetchIdeasDto dto1 = new FetchIdeasDto(1L, "category1");
            FetchIdeasDto dto2 = new FetchIdeasDto(1L, "category1");
    
            // Consistent: multiple invocations should consistently return the same value
            assertEquals(dto1.hashCode(), dto1.hashCode());
    
            // Equal objects must have equal hash codes
            assertEquals(dto1.hashCode(), dto2.hashCode());

        }
    
        // Test for verifying the toString method
        @Test
        void testToStringMethod() {
            FetchIdeasDto dto = new FetchIdeasDto(1L, "category1");
    
            String expectedString = "FetchIdeasDto(id=1, categoryCode=category1)";
            String actualString = dto.toString();
    
            // Verify toString contains the correct representation of the object
            assertEquals(expectedString, actualString);
        }
}
