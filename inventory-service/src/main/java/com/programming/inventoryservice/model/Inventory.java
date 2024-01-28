// Declare package for class
package com.programming.inventoryservice.model;
// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;

// Marks class as Jpa entity (a persistent domain object)
@Entity
// Specifies table in database with which entity is mapped
@Table(name = "t_inventory")
// Enables Builder pattern for object creation
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id // marks field below as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryCode;
    private String ideaDescription;
    private String dB;
    private String backendStructure;
    private String frontendStructure;
    private String mainComponents;
}