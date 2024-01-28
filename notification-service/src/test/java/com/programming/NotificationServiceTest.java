// Define package 
package com.programming;

// Import classes
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NotificationServiceTest {

    // Declare ListAppender to hold logging events
    private ListAppender<ILoggingEvent> listAppender;

    // BeforeEach Method runs before each test method
    @BeforeEach
    void setUp() {
        // Get Logger instance for NotificationServiceApplication
        Logger logger = (Logger) LoggerFactory.getLogger(NotificationServiceApplication.class);
        // Instantiate listAppender to capture log events
        listAppender = new ListAppender<>();
        // Start appender to begin capturing log events
        listAppender.start();
        // Add listAppender to logger to capture output
        logger.addAppender(listAppender);
    }
    
    // Test case to simulate application's main method execution
    @Test
    public void main() {
        // Arguments array typically passed to the main method; empty here as it's not used
        String[] args = {};
        // Call main method to initiate the application
        NotificationServiceApplication.main(args);
    }

    @Test
    void testHandleNotification() {
        // Create instance of NotificationServiceApplication to test
        NotificationServiceApplication notificationServiceApplication = new NotificationServiceApplication();
        // Create mock FetchSuccessfulEvent 
        FetchSuccessfulEvent mockEvent = new FetchSuccessfulEvent("123");

        // Call handleNotification method, simulating Kafka message reception
        notificationServiceApplication.handleNotification(mockEvent);

        // Assert listAppender contains log event with expected message
        assertTrue(listAppender.list.stream()
                .anyMatch(event -> event.getFormattedMessage()
                .contains("Received Notification for Request - 123")));
    }
}
