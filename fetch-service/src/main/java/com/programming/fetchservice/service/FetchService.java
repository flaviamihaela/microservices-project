package com.programming.fetchservice.service;

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

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FetchService {

    private final FetchRepository fetchRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, FetchSuccessfulEvent> kafkaTemplate;

    public String placeFetch(FetchRequest fetchRequest) {
        Fetch fetch = new Fetch();
        fetch.setFetchNumber(UUID.randomUUID().toString());

        List<FetchIdeas> fetchIdeas = fetchRequest.getFetchIdeasDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        fetch.setFetchIdeasList(fetchIdeas);

        List<String> skuCodes =  fetch.getFetchIdeasList().stream()
                                .map(FetchIdeas::getSkuCode)
                                .toList();

        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope spanInScope = tracer.withSpanInScope(inventoryServiceLookup.start())) {

            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", 
                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
            boolean allProjectsAvailable = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

            if(allProjectsAvailable){
                fetchRepository.save(fetch);
                kafkaTemplate.send("notificationTopic", new FetchSuccessfulEvent(fetch.getFetchNumber()));
                return "Out of fresh ideas?! Check your email!";
            } else {
                throw new IllegalArgumentException("There are no new ideas. You haven't brainstormed in a while, huh?");
            }

        } finally {
            inventoryServiceLookup.tag("call", "inventory-service");
        }

    }

    private FetchIdeas mapToDto(FetchIdeasDto fetchIdeasDto) {
        FetchIdeas fetchIdeas = new FetchIdeas();
        fetchIdeas.setPrice(fetchIdeasDto.getPrice());
        fetchIdeas.setQuantity(fetchIdeasDto.getQuantity());
        fetchIdeas.setSkuCode(fetchIdeasDto.getSkuCode());
        return fetchIdeas;
    }
}