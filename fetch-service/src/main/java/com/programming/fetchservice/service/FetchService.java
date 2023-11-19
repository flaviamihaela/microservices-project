// Define package for class
package com.programming.fetchservice.service;

// Import classes and annotations
import com.programming.fetchservice.dto.FetchIdeasDto;
import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.model.Fetch;
import com.programming.fetchservice.model.FetchIdeas;
import com.programming.fetchservice.repository.FetchRepository;

import brave.Tracer;
import brave.Span;

import com.programming.fetchservice.dto.InventoryResponse;
import com.programming.fetchservice.event.FetchSuccessfulEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Arrays;
import java.util.UUID;

// Declares class as a Spring Service component
@Service
// Automatically generates a constructor with required arguments
@RequiredArgsConstructor
// Indicates that methods in class should operate within a transactional context
@Transactional
// Provides logger
@Slf4j
public class FetchService {

    // Dependency injection
    private final FetchRepository fetchRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, FetchSuccessfulEvent> kafkaTemplate;

    // Method for processing a FetchRequest 
    public String placeFetch(FetchRequest fetchRequest) {
        Fetch fetch = new Fetch();
        fetch.setFetchNumber(UUID.randomUUID().toString());

        // Converts FetchIdeasDto list from request to FetchIdeas entities
        List<FetchIdeas> fetchIdeas = fetchRequest.getFetchIdeasDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
     
        fetch.setFetchIdeasList(fetchIdeas);

         // Extracts category codes from FetchIdeas for inventory check
        List<String> categoryCodes =  fetch.getFetchIdeasList().stream()
                                .map(FetchIdeas::getCategoryCode)
                                .toList();

        // Creates a new tracing span for inventory service lookup
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        // Wraps the inventory service call within a tracing span
        try (Tracer.SpanInScope spanInScope = tracer.withSpanInScope(inventoryServiceLookup.start())) {

            // Calls inventory service and checks if all requested items are in stock
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", 
                    uriBuilder -> uriBuilder.queryParam("categoryCode", categoryCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
            boolean allProjectsAvailable = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

            // If all items are available, save the fetch and send a Kafka event
            if(allProjectsAvailable){
                fetchRepository.save(fetch);
                kafkaTemplate.send("notificationTopic", new FetchSuccessfulEvent(fetch.getFetchNumber()));
                return "Out of fresh ideas?! Check your email!";
            } else {
                log.error("There are no new ideas. You haven't brainstormed in a while, huh?");
                throw new IllegalArgumentException("There are no new ideas. You haven't brainstormed in a while, huh?");
            }

        } finally {
            // Adds a tag to the tracing span
            inventoryServiceLookup.tag("call", "inventory-service");
        }

    }

    // Helper method to map FetchIdeasDto to FetchIdeas entity
    private FetchIdeas mapToDto(FetchIdeasDto fetchIdeasDto) {
        FetchIdeas fetchIdeas = new FetchIdeas();
        fetchIdeas.setQuantity(fetchIdeasDto.getQuantity());
        fetchIdeas.setCategoryCode(fetchIdeasDto.getCategoryCode());
        return fetchIdeas;
    }
}