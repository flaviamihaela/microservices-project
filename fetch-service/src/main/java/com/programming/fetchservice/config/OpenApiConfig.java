package com.programming.fetchservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
 
@Configuration
public class OpenApiConfig {
 
    private final static String key = "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";
 
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + key);
            return execution.execute(request, body);
        });
        return template;
    }
}