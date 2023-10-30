// Define package for this class
package com.programming.projectservice.model;

// Importing Lombok annotations
// to generate boilerplate getters, setters, constructors, etc.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Importing Spring Data annotations
// for MongoDB mapping and identification of primary key
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder // use Builder pattern for object creation
@Data
public class Project {

    @Id // marks the below field as the primary key in MongoDB
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}