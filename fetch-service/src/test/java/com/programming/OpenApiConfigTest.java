//Define package for class
package com.programming;

//Imports
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import com.programming.fetchservice.config.OpenApiConfig;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OpenApiConfig.class)
public class OpenApiConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void restTemplateBeanShouldBeConfigured() {
        assertNotNull(restTemplate, "RestTemplate bean should not be null");

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        assertFalse(interceptors.isEmpty(), "RestTemplate should have at least one interceptor configured");

    }
}
