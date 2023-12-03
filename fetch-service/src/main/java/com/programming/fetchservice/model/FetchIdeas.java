// Define package for class
package com.programming.fetchservice.model;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Marks class as a Jpa entity (persistent domain object)
@Entity
// Specifies the table in db with which the entity is mapped
@Table(name = "t_fetch_ideas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeas {
    @Id // marks the below field as the primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryCode;

}