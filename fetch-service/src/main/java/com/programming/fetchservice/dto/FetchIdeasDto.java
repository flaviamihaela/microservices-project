// Define package for class
package com.programming.fetchservice.dto;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, equals, hashCode and toString methods
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeasDto {
    private Long id;
    private String categoryCode;
    private Integer quantity;
}