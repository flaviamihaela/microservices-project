// Define package for class
package com.programming;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, equals, hashCode and toString methods
@Data
// Generates constructor with arguments for all fields 
@AllArgsConstructor
// Generates constructor with no arguments 
@NoArgsConstructor

public class FetchSuccessfulEvent {
    // Due to @Data annotation, getter and setter for this field automatically generated
    private String fetchNumber;
}
