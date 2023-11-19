// Define package for class
package com.programming.fetchservice.dto;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// Generates getters, setters, equals, hashCode and toString methods
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchRequest {
    private List<FetchIdeasDto> fetchIdeasDtoList;
}