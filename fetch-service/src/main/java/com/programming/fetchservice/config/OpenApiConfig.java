// Define packeage for class
package com.programming.fetchservice.config;

//Imports
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//Configuration class for setting up OpenAPI-related beans.
@Configuration
public class OpenApiConfig {

    // Injects value of 'openai.api.key' property from application's configuration into 'key' field.    
    @Value("${openai.api.key}")
    private String key;
 
    //Defines a RestTemplate bean with custom configuration - intended for making HTTP requests
    @Bean
    public RestTemplate restTemplate() {
         // Create a new RestTemplate instance.
        RestTemplate template = new RestTemplate();
        // Add interceptor to RestTemplate to modify each request before it's sent
        template.getInterceptors().add((request, body, execution) -> {
            // Add an 'Authorization' header with the 'Bearer' token using the injected API key.
            request.getHeaders().add("Authorization", "Bearer " + key);
            // Continue the execution of the request with the modified headers.
            return execution.execute(request, body);
        });
        // Return configured RestTemplate instance.
        return template;
    }
}