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
            inventory.setQuantity(1);
            inventory.setTechStack("L&T");
            inventory.setDB("L&T");
            inventory.setBackendStructure("L&T");
            inventory.setFrontendStructure("L&T");
            inventory.setMainComponents("L&T");


            Inventory inventory1 = new Inventory();
            inventory1.setCategoryCode("WA");
            inventory1.setQuantity(0);
            inventory1.setTechStack("WA");
            inventory1.setDB("WA");
            inventory1.setBackendStructure("WA");
            inventory1.setFrontendStructure("WA");
            inventory1.setMainComponents("WA");

            Inventory inventory2 = new Inventory();
            inventory2.setCategoryCode("MA");
            inventory2.setQuantity(1);
            inventory2.setTechStack("MA");
            inventory2.setDB("MA");
            inventory2.setBackendStructure("MA");
            inventory2.setFrontendStructure("MA");
            inventory2.setMainComponents("MA");

            Inventory inventory3 = new Inventory();
            inventory3.setCategoryCode("IoT");
            inventory3.setQuantity(1);
            inventory3.setTechStack("IoT");
            inventory3.setDB("IoT");
            inventory3.setBackendStructure("IoT");
            inventory3.setFrontendStructure("IoT");
            inventory3.setMainComponents("IoT");

            // Saves created Inventory objects into the repository
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
        };
    }
}