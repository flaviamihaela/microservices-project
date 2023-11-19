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
            // Creates and setting up Inventory objects with specific details
            Inventory inventory = new Inventory();
            inventory.setCategoryCode("Librarires&Tools");
            inventory.setQuantity(1);

            Inventory inventory1 = new Inventory();
            inventory1.setCategoryCode("Web Apps");
            inventory1.setQuantity(0);

            Inventory inventory2 = new Inventory();
            inventory2.setCategoryCode("Mobile Apps");
            inventory2.setQuantity(1);

            Inventory inventory3 = new Inventory();
            inventory3.setCategoryCode("Internet of Things");
            inventory3.setQuantity(1);

            // Saves created Inventory objects into the repository
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
        };
    }
}