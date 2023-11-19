// Define package for class
package com.programming;

// Import classes and annotations
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

// Defines class as main entry point for Spring Boot Application 
@SpringBootApplication
// Enables logging
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    // Kafka listener method that listens to a specified topic
    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(FetchSuccessfulEvent fetchSuccessfulEvent) {
        // Method triggered when message received in 'notificationTopic' Kafka topic
        // Log created indicating receipt of notification
        log.info("Received Notification for Request - {}", fetchSuccessfulEvent.getFetchNumber());
    }
}