
//Define package 
package com.programming.fetchservice.event;
//Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FetchSuccessfulEvent {
    private String fetchNumber;
}
