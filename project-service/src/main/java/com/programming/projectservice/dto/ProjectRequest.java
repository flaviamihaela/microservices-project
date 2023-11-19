// Define package for class
package com.programming.projectservice.dto;

// Importing classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters ,setters, equals, hashCode and toString methods
@Data
// Enables Builder pattern for object creation
@Builder
// Generates constructor with arguments for all fields
@AllArgsConstructor
// Generates constructor with no arguments
@NoArgsConstructor
public class ProjectRequest {
    private String name;
    private String description;
}