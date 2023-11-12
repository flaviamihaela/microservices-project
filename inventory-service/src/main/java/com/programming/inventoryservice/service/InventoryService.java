package com.programming.inventoryservice.service;

import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> categoryCode) {
        log.info("Checking Inventory");
        return inventoryRepository.findByCategoryCodeIn(categoryCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                    .categoryCode(inventory.getCategoryCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build()
                ).toList();
    }
}