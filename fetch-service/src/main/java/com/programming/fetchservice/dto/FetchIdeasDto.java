// Define package for class
package com.programming.fetchservice.dto;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
// Generates getters, setters, equals, hashCode and toString methods
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeasDto {
    private Long id;
    @NotEmpty(message = "Category code must not be empty")
    private String categoryCode;
}