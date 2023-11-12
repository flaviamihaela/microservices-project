// Define package for this class
package com.programming.projectservice.dto;

// Importing Lombok annotations
// to generate boilerplate getters, setters, constructors, etc.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
}