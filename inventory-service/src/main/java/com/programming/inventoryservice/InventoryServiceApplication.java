package com.programming.inventoryservice;

import com.programming.inventoryservice.model.Inventory;
import com.programming.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
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

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
        };
    }
}