// Package for test class
package com.programming;

// Imports
import org.junit.jupiter.api.Test;
import com.programming.fetchservice.dto.Message;
import static org.junit.jupiter.api.Assertions.*;

class FetchMessageTest {

    // Test to verify Message DTO's constructor
    @Test
    void testMessageCreationAndAccessors() {
        // Creating Message instance with specified attributes
        String role = "Admin";
        String content = "This is a test message";

        Message message = new Message(role, content);

        // Asserting getters return correct values
        assertEquals(role, message.getRole());
        assertEquals(content, message.getContent());
    }

    // Test to verify set and get methods for each field in Message
    @Test
    void testSetAndGetMethods() {
        // Creating Message instance and setting new values
        Message message = new Message();
        String newRole = "User";
        String newContent = "New test message";

        message.setRole(newRole);
        message.setContent(newContent);

        // Asserting each field was updated correctly
        assertEquals(newRole, message.getRole());
        assertEquals(newContent, message.getContent());
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        Message message = new Message("Admin", "This is a test message");
        String notAMessage = "Not a Message";

        // Asserting that a Message is not equal to null
        assertNotEquals(message, null);

        // Asserting that a Message is not equal to an object of a different class
        assertNotEquals(message, notAMessage);
    }

    // Test for equals method
    @Test
    void testMessageEquals() {
        Message message1 = new Message("Admin", "This is a test message");
        Message message2 = new Message("Admin", "This is a test message");
        Message message3 = new Message("User", "This is a different message");

        // Test reflexivity
        assertEquals(message1, message1);

        // Test symmetry
        assertEquals(message1, message2);
        assertEquals(message2, message1);

        // Test inequality
        assertNotEquals(message1, message3);
    }

    // Test for hashcode method
    @Test
    void testMessageHashCode() {
        Message message1 = new Message("Admin", "This is a test message");
        Message message2 = new Message("Admin", "This is a test message");

        // Test consistency
        assertEquals(message1.hashCode(), message1.hashCode());

        // Test equality
        assertEquals(message1.hashCode(), message2.hashCode());

        // Creating a new instance with different data
        Message message3 = new Message("User", "This is a different message");

        // Test non-equality
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    // Test for tostring method
    @Test
    void testMessageToString() {
        Message message = new Message("Admin", "This is a test message");
        // The expected format is based on the Lombok @ToString output
        String expectedToString = "Message(role=Admin, content=This is a test message)";
        assertEquals(expectedToString, message.toString());
    }
}
