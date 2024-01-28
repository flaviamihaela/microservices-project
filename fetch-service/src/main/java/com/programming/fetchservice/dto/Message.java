//Define package for class
package com.programming.fetchservice.dto;

//Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generates getters, setters, equals, hashCode and toString methods
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    
    private String role;
    private String content;
}