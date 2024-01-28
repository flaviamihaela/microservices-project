//Define package for this class
package com.programming.fetchservice.dto;

//Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import java.util.List;
 
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatResponse {

    // A list of 'Choice' objects representing choices in chat response
    private List<Choice> choices;
 
    //'Choice' class represents an individual choice in chat response 
    //Static - it's not tied to specific instance of 'ChatResponse' and can be instantiated independently
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
 
        private int index;
        private Message message;
    }
}