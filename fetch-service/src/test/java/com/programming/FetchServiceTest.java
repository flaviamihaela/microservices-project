
//Define package for class
package com.programming;

// Import classes
import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.dto.FetchIdeasDto;
import com.programming.fetchservice.dto.InventoryResponse;
import com.programming.fetchservice.event.FetchSuccessfulEvent;
import com.programming.fetchservice.model.Fetch;
import com.programming.fetchservice.repository.FetchRepository;
import com.programming.fetchservice.service.FetchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import brave.Span;
import brave.Tracer;
import reactor.core.publisher.Mono;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class FetchServiceTest {
     // Mock dependencies for FetchService
    @Mock
    private FetchRepository fetchRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private Tracer tracer;

    @Mock
    private Span span;

    @Mock
    private KafkaTemplate<String, FetchSuccessfulEvent> kafkaTemplate;

    @Mock
    private RestTemplate restTemplate;

    // Inject mocks into the FetchService instance for testing
    @InjectMocks
    private FetchService fetchService;

    // Set up common mock behavior for all tests in this class
    @BeforeEach
    void setUp() {
        // Configure tracer mock to return a mock span
        when(tracer.nextSpan()).thenReturn(span);
        
        // Mock behavior for Span to support method chaining
        when(span.name(anyString())).thenReturn(span); 
        when(span.start()).thenReturn(span);
    }

    // Test case for verifying behavior when all requested items are available
    @Test
    void whenPlaceFetchWithAllItemsAvailable_thenSuccessMessageReturned() {
        // Arrange test data and mock interactions
        FetchRequest fetchRequest = new FetchRequest();
        fetchRequest.setFetchIdeasDtoList(List.of(new FetchIdeasDto(null, "categoryCode1")));
    
        // Initialize InventoryResponse with non-null values for all accessed properties
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setBackendStructure("ValidBackendStructure");
        inventoryResponse.setFrontendStructure("ValidFrontendStructure"); 
        inventoryResponse.setIdeaDescription("ValidIdeaDescription");
        inventoryResponse.setMainComponents("ValidMainComponents");
        inventoryResponse.setDB("ValidDatabase");
    
        InventoryResponse[] inventoryResponses = new InventoryResponse[]{inventoryResponse};
    
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(InventoryResponse[].class)).thenReturn(Mono.just(inventoryResponses));
    
        when(fetchRepository.save(any(Fetch.class))).thenAnswer(invocation -> invocation.getArgument(0));
    
        // Act by calling method under test
        String result = fetchService.placeFetch(fetchRequest);
    
        // Assert expected outcome
        assertEquals("Out of fresh ideas?! Check your email!", result);
        verify(kafkaTemplate).send(eq("notificationTopic"), any());
    }
    

    // Test case for verifying behavior when requested items are not available
    @Test
    void whenPlaceFetchAndItemsNotAvailable_thenExceptionThrown() {
         // Arrange test data and mock interactions for no available items
        FetchRequest fetchRequest = new FetchRequest();
        fetchRequest.setFetchIdeasDtoList(List.of(new FetchIdeasDto(null, "categoryCode1"))); // Adjusted here

        InventoryResponse[] inventoryResponses = new InventoryResponse[]{}; // Empty, simulating no items available
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(InventoryResponse[].class)).thenReturn(Mono.just(inventoryResponses));

        // Act and assert expected exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fetchService.placeFetch(fetchRequest);
        });

        assertEquals("Well this is awkward... You haven't brainstormed in a while, huh?", exception.getMessage());
    }
}
