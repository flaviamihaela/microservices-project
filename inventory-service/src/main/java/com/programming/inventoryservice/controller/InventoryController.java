// Define package for class
package com.programming.inventoryservice.controller;

// Import classes and annotations
import com.programming.inventoryservice.dto.InventoryResponse;
import com.programming.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Defines controller - return value of methods bound to web response body
@RestController
// Base URL path for all request mappings in this controller
@RequestMapping("/api/inventory")
// Generates a constructor with requires fields
@RequiredArgsConstructor
public class InventoryController {

    // Dependency injection
    private final InventoryService inventoryService;

    // Handles HTTP GET requests matched with given URI expression
    @GetMapping
    // Marks method/exception class with status code and reason to be returned
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> categoryCode) {
        // Delegates call to inventoryService to check if items in stock and returns result
        return inventoryService.isInStock(categoryCode);
    }
}