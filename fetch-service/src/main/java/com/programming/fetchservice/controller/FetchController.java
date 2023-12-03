// Define package for class
package com.programming.fetchservice.controller;

// Import classes and annotations
import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.service.FetchService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Marks this class as controller where methods return domain object
@RestController
// Base URL path for all request mappings in this controller
@RequestMapping("/api/fetch")
// Automatically generates a constructor with required fields

@RequiredArgsConstructor
public class FetchController {

    // Dependency injection
    private final FetchService fetchService;

    // Marks method to handle POST request
    @PostMapping
    //Marks method with the HTTP status code
    @ResponseStatus(HttpStatus.CREATED)
    // Handles integration points and failing fast
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    // Sets a time limit for the call
    @TimeLimiter(name="inventory")
    // Specifies method should be retried in case of failure
    @Retry(name = "inventory")
    public CompletableFuture<String> placeFetch(@RequestBody FetchRequest fetchRequest) {
        // Method uses CompletableFuture to asynchronously handle request using FetchService
        return CompletableFuture.supplyAsync(()->fetchService.placeFetch(fetchRequest));
    }

    // Fallback method for the circuit breaker
    // Called when placeFetch method fails and triggers circuit breaker
    public CompletableFuture<String> fallbackMethod(FetchRequest fetchRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()-> "Oops! Something went wrong, please try again later!");    }

    // Marks method to handle GET request
    @GetMapping
    //Marks method with the HTTP status code
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> chat(@RequestParam("prompt") String prompt) {
        // Method uses CompletableFuture to asynchronously handle request using FetchService
        return CompletableFuture.supplyAsync(()->fetchService.chat(prompt));
    }

}