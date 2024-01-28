// Declare package for class
package com.programming.inventoryservice.repository;

// Import classes and annotations
import com.programming.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface extends JpaRepository to provide CRUD operations
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCategoryCodeIn(List<String> categoryCode);
    Inventory findFirstByCategoryCode(String categoryCode);
}