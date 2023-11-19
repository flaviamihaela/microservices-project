// Define package for class
package com.programming.projectservice.dto;

// Importing classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, toString, equals, and hashCode methods
@Data
// Enables Builder pattern for object creation
@Builder
// Generates a constructor with arguments for all fields
@AllArgsConstructor
// Generates a constructor with no arguments
@NoArgsConstructor
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
}