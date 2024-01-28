// Define package for class
package com.programming.fetchservice.dto;

//Imports
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Generates getters, setters, equals, hashCode and toString methods
@Data
public class ChatRequest {
 
    private String model;
    private List<Message> messages;
 
    //Constructor for ChatRequest which initializes the model and messages list.

    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}