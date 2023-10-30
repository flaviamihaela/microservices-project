package com.programming.fetchservice.controller;

import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.service.FetchService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fetch")
@RequiredArgsConstructor
public class FetchController {

    private final FetchService fetchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeFetch(@RequestBody FetchRequest fetchRequest) {
        return CompletableFuture.supplyAsync(()->fetchService.placeFetch(fetchRequest));
    }

    public CompletableFuture<String> fallbackMethod(FetchRequest fetchRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()-> "Oops! Something went wrong, please try again later!");    }
}