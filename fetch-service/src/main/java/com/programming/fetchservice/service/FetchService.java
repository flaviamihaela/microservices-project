// Define package for class
package com.programming.fetchservice.service;

import com.programming.fetchservice.dto.ChatRequest;
import com.programming.fetchservice.dto.ChatResponse;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.programming.fetchservice.dto.ChatRequest;
import com.programming.fetchservice.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
    
    @Autowired
    private RestTemplate template;

    // Method for processing a FetchRequest 
    public String placeFetch(FetchRequest fetchRequest) {
        Fetch fetch = new Fetch();

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

        log.info(Arrays.toString(categoryCodes.toArray()));

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

            boolean allProjectsAvailable = inventoryResponseArray.length == categoryCodes.size();

            // If all items are available, save the fetch and send a Kafka event
            if(allProjectsAvailable){
                // Build the prompt for the ChatGPT API
                //String prompt = buildPrompt(inventoryResponseArray);
                String prompt = "Hello";
                log.info(prompt);
                String codeSkeleton = chat(prompt);
                log.info(codeSkeleton);

                fetch.setFetchNumber(UUID.randomUUID().toString());
                fetchRepository.save(fetch);
                kafkaTemplate.send("notificationTopic", new FetchSuccessfulEvent(codeSkeleton));
                return "Out of fresh ideas?! Check your email!";
            } else {
                log.error("Well this is awkward... You haven't brainstormed in a while, huh?");
                throw new IllegalArgumentException("Well this is awkward... You haven't brainstormed in a while, huh?");
            }
            
        } finally {
            // Adds a tag to the tracing span
            inventoryServiceLookup.tag("call", "inventory-service");
        }

    }

    // Helper method to map FetchIdeasDto to FetchIdeas entity
    private FetchIdeas mapToDto(FetchIdeasDto fetchIdeasDto) {
        FetchIdeas fetchIdeas = new FetchIdeas();
        fetchIdeas.setCategoryCode(fetchIdeasDto.getCategoryCode());
        return fetchIdeas;
    }
    // Helper method to build the prompt for ChatGPT
    private String buildPrompt(InventoryResponse[] inventoryResponses) {

        InventoryResponse inventoryResponse =  inventoryResponses[0];

        log.info(inventoryResponse.getBackendStructure().toString());

        String combined = String.format(
            "Backend: %s, Frontend: %s, Tech Stack: %s, Main Components: %s, Database: %s",
            inventoryResponse.getBackendStructure().toString(),
            inventoryResponse.getFrontendStructure().toString(),
            inventoryResponse.getTechStack().toString(),
            inventoryResponse.getMainComponents().toString(),
            inventoryResponse.getDB().toString()
        );

        combined = "In 1 sentence how should i start the following programming project " + combined;
        return combined;
    }

    // Method to generate the skeleton for the fetched idea
    public String chat(String prompt) {
        ChatRequest request = new ChatRequest("gpt-3.5-turbo", prompt);
        try {
            ChatResponse response = template.postForObject(
                    "https://api.openai.com/v1/chat/completions", request, ChatResponse.class);
                    if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                        return response.getChoices().get(0).getMessage().getContent();
                    } else {
                        return "Received an empty response";
                    }        } catch (Exception e) {
            return e.getMessage();
        }
    }

}