// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;

import com.programming.fetchservice.dto.ChatRequest;
import com.programming.fetchservice.dto.Message;
import static org.junit.jupiter.api.Assertions.*;

class FetchChatRequestTest {

    // Test to verify ChatRequest's constructor, getters and setters
    @Test
    void testChatRequestCreationAndAccessors() {
        // Creating ChatRequest instance with specified attributes
        String model = "testModel";
        String prompt = "Hello";

        ChatRequest chatRequest = new ChatRequest(model, prompt);

        // Asserting constructor and getters return correct values
        assertEquals(model, chatRequest.getModel());
        assertEquals(1, chatRequest.getMessages().size());
        assertEquals("user", chatRequest.getMessages().get(0).getRole());
        assertEquals(prompt, chatRequest.getMessages().get(0).getContent());

        // Setting new values
        String newModel = "newTestModel";
        Message newMessage = new Message("admin", "Welcome");
        chatRequest.setModel(newModel);
        chatRequest.getMessages().add(newMessage);

        // Asserting setters work correctly
        assertEquals(newModel, chatRequest.getModel());
        assertEquals(2, chatRequest.getMessages().size());
        assertEquals(newMessage, chatRequest.getMessages().get(1));
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        // Creating ChatRequest instance with specified attributes
        ChatRequest chatRequest = new ChatRequest("testModel", "Hello");
        String notAChatRequest = "Not a ChatRequest";

        // Asserting that ChatRequest is not equal to null
        assertNotEquals(chatRequest, null);

        // Asserting that ChatRequest is not equal to an object of a different class
        assertNotEquals(chatRequest, notAChatRequest);
    }

    // Test for equals and hashCode methods
    @Test
    void testChatRequestEqualsAndHashCode() {
        // Creating ChatRequest instances with specified attributes
        ChatRequest chatRequest1 = new ChatRequest("testModel", "Hello");
        ChatRequest chatRequest2 = new ChatRequest("testModel", "Hello");
        ChatRequest chatRequestDifferent = new ChatRequest("testModel", "Goodbye");

        assertEquals(chatRequest1, chatRequest2);
        assertEquals(chatRequest1.hashCode(), chatRequest2.hashCode());
        assertNotEquals(chatRequest1, chatRequestDifferent);
        assertNotEquals(chatRequest1.hashCode(), chatRequestDifferent.hashCode());
    }

    // Test for toString method
    @Test
    void testChatRequestToString() {
        // Creating ChatRequest instance with specified attributes
        ChatRequest chatRequest = new ChatRequest("testModel", "Hello");
        String chatRequestString = chatRequest.toString();
        assertNotNull(chatRequestString);
        assertTrue(chatRequestString.contains("testModel"));
        assertTrue(chatRequestString.contains("Hello"));
    }
}
