// Declare package for class
package com.programming.inventoryservice;

// Import classes and annotations
import com.programming.inventoryservice.model.Inventory;
import com.programming.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


// Defines class as entry point for Spring Boot Application
@SpringBootApplication
// Enables class to register with Eureka discovery server
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    // Tells Spring that this method returns a bean to be managed by the Spring container
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            // Creates and sets up Inventory objects with specific details
            Inventory inventory = new Inventory();
            inventory.setCategoryCode("L&T");
            inventory.setTechStack(" ");
            inventory.setDB("MongoDB");
            inventory.setBackendStructure("Spring Boot with Maven");
            inventory.setFrontendStructure("React");
            inventory.setMainComponents("RESTful API server, build system for dependency management and project lifecycle tasks, single-page application with components for user authentication, library and tool listings, detailed views, and documentation access");


            Inventory inventory1 = new Inventory();
            inventory1.setCategoryCode("WA");
            inventory1.setTechStack(" ");
            inventory1.setDB("MongoDB");
            inventory1.setBackendStructure("Express - Node js");
            inventory1.setFrontendStructure("React");
            inventory1.setMainComponents("Server handling API requests, middleware for routing and authentication, server-side logic, and a frontend with components for navigation, data presentation, and interactive user interfaces");

            Inventory inventory2 = new Inventory();
            inventory2.setCategoryCode("MA");
            inventory2.setTechStack(" ");
            inventory2.setDB("PostgreSQL");
            inventory2.setBackendStructure("Django");
            inventory2.setFrontendStructure("React");
            inventory2.setMainComponents("REST framework for creating API endpoints, cross-platform user interface, data management, along with authentication, routing, and state management functionalities.");

            Inventory inventory3 = new Inventory();
            inventory3.setCategoryCode("IoT");
            inventory3.setTechStack(" ");
            inventory3.setDB("MongoDB");
            inventory3.setBackendStructure("Flask");
            inventory3.setFrontendStructure("Vue.js");
            inventory3.setMainComponents("Flask-SocketIO for real-time communication, a RESTful API for device interaction, a time-series or NoSQL database for data storage, reactive frontend interface capable of displaying real-time device data and controls.");

            // Saves created Inventory objects into the repository
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
        };
    }
}