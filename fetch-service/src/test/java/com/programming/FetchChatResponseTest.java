// Package declaration
package com.programming;  

// Imports
import org.junit.jupiter.api.Test;
import com.programming.fetchservice.dto.ChatResponse;
import com.programming.fetchservice.dto.Message;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class FetchChatResponseTest {
    
    @Test
    void testChatResponseCreationAndModification() {
        // Create a new Message instance and create a list of choices
        Message message = new Message("User", "Hello World");  
        ChatResponse.Choice choice = new ChatResponse.Choice(1, message);  
        List<ChatResponse.Choice> choices = List.of(choice); 
        // Create a ChatResponse with the choices and assert choices are correctly set
        ChatResponse chatResponse = new ChatResponse(choices);  
        assertEquals(choices, chatResponse.getChoices());  
        
        // Create a new Choice, modify choices in ChatResponse and assert modification is successful
        ChatResponse.Choice newChoice = new ChatResponse.Choice(2, new Message("Admin", "Welcome"));  
        chatResponse.setChoices(List.of(newChoice));  
        assertEquals(List.of(newChoice), chatResponse.getChoices());  
    }
    
    @Test
    void testChoiceCreationAndModification() {
        // Create a new Message instance and a Choice with message
        Message message = new Message("User", "Hello World");  
        ChatResponse.Choice choice = new ChatResponse.Choice(1, message);
        // Assert index and message of Choice are correctly set
        assertEquals(1, choice.getIndex());  
        assertEquals(message, choice.getMessage());  
        
        // Create new message, modify the Choice, and assert modifications are successful
        Message newMessage = new Message("Admin", "Welcome");  
        choice.setIndex(2);  
        choice.setMessage(newMessage);  
        assertEquals(2, choice.getIndex());  
        assertEquals(newMessage, choice.getMessage());  
    }
    
    @Test
    void testChatResponseEqualsHashCodeAndToString() {
        // Create new Message instance, a Choice, and two ChatResponse objects with identical content
        Message message = new Message("User", "Hello World");  
        ChatResponse.Choice choice = new ChatResponse.Choice(1, message);  
        ChatResponse chatResponse1 = new ChatResponse(List.of(choice));  
        ChatResponse chatResponse2 = new ChatResponse(List.of(new ChatResponse.Choice(1, message)));  
        
        // Assert  two ChatResponse objects are equal and have same hash code
        assertEquals(chatResponse1, chatResponse2);  
        assertEquals(chatResponse1.hashCode(), chatResponse2.hashCode());  
        assertNotNull(chatResponse1.toString());  // Assert toString() provides a non-null String representation
        
        // Create a different ChatResponse and assert it is not equal to the first one
        ChatResponse chatResponseDifferent = new ChatResponse(List.of(new ChatResponse.Choice(2, new Message("Admin", "Welcome"))));  
        assertNotEquals(chatResponse1, chatResponseDifferent);  
    }
    
    @Test
    void testChoiceEqualsHashCodeAndToString() {
        // Create two Choice objects with identical content
        Message message = new Message("User", "Hello World");  
        ChatResponse.Choice choice1 = new ChatResponse.Choice(1, message);  
        ChatResponse.Choice choice2 = new ChatResponse.Choice(1, new Message("User", "Hello World"));  
        
        // Assert the two Choice objects are equal and have same hash code
        assertEquals(choice1, choice2);  
        assertEquals(choice1.hashCode(), choice2.hashCode());  
        assertNotNull(choice1.toString());  // Assert toString() provides a non-null String representation
        
        // Create a different Choice and assert it is not equal to the first one
        ChatResponse.Choice choiceDifferent = new ChatResponse.Choice(2, new Message("Admin", "Welcome"));  
        assertNotEquals(choice1, choiceDifferent);  
    }
}
