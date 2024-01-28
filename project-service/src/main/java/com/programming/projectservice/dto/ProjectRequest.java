// Define package for class
package com.programming.projectservice.dto;

// Importing classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


// Generates getters ,setters, equals, hashCode and toString methods
@Data
// Enables Builder pattern for object creation
@Builder
// Generates constructor with arguments for all fields
@AllArgsConstructor
// Generates constructor with no arguments
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "Name is mandatory")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @NotBlank(message = "Description is mandatory")
    @NotEmpty(message = "Description must not be empty")
    private String description;
}