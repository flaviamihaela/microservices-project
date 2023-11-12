package com.programming.inventoryservice.repository;

import com.programming.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCategoryCodeIn(List<String> categoryCode);
}