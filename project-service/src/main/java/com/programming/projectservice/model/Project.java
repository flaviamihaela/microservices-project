// Define package for class
package com.programming.projectservice.model;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Marks class as MongoDB document stored in 'project' collection
@Document(value = "project")
// Generates constructor with arguments for all fields
@AllArgsConstructor
// Generates constructor with no arguments
@NoArgsConstructor
// Enables Builder pattern for object creation
@Builder
// Generates getters, setters, toString, equals, and hashCode methods
@Data
public class Project {

    @Id // marks the below field as the primary key in MongoDB
    private String id;
    @Indexed(unique = true) 
    private String name;
    private String description;
}