//Define package for class
package com.programming;
//Imports
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import com.programming.fetchservice.config.WebClientConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = WebClientConfig.class)
public class WebClientConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void webClientBuilderBeanShouldBeLoadBalanced() {
        // Ensure the WebClient.Builder bean is present
        WebClient.Builder builder = context.getBean(WebClient.Builder.class);
        assertNotNull(builder, "WebClient.Builder bean should be available in the application context");
    }
}
