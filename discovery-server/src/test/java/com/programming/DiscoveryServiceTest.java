// Define package for class
package com.programming;

// Imports
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import com.programming.discoveryserver.DiscoveryServerApplication;

// Indicates test should run with Spring Application context
@SpringBootTest(classes = DiscoveryServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscoveryServiceTest {

    // Injects Environment bean from Spring context to access application properties
    @Autowired
    private Environment environment;

    //Test verifies Spring application context loads successfully and 'Environment' bean is available
    @Test
    void contextLoads() {
        // Assert environment bean is not null
        assertNotNull(environment);
        // Retrieve and assert random server port is set, indicating web environment is correctly configured
        String port = environment.getProperty("local.server.port");
        assertNotNull(port);
        System.out.println("Server is running on port: " + port);
    }
    
    // Test method simulates startup of main application
    // Ensures application can start with specified configuration and classes
    @Test
    public void mainApplicationStart() {
        String[] args = {}; // Empty arguments for application start
    
        // Set a system property to use a random port
        System.setProperty("server.port", "0");
    
        // Attempt to start application and assert it starts successfully
        try (ConfigurableApplicationContext context = SpringApplication.run(DiscoveryServerApplication.class, args)) {
            assertNotNull(context);
            assertTrue(context.isRunning());
        } finally {
            System.clearProperty("server.port");
        }
    }

}
