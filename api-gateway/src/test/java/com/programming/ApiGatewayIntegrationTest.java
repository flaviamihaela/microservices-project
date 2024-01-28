// Package for test class
package com.programming;

// Import classes and annotations
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import static org.junit.jupiter.api.Assertions.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

// Annotation - class uses Testcontainers for integration testing
@Testcontainers
public class ApiGatewayIntegrationTest {

        // Network setup for Testcontainers - allows containers to communicate
        private static Network network = Network.newNetwork();
        
        // Container setup for Zipkin tracing system
        @Container
        private final static GenericContainer<?> zipkin = new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin"))
                .withNetwork(network)
                .withNetworkAliases("zipkin")
                .withExposedPorts(9411);

        // Container setup for MongoDB
        @Container
        private final static GenericContainer<?> mongo = new GenericContainer<>(DockerImageName.parse("mongo:4.4.14-rc0-focal"))
                .withNetwork(network)        
                .withNetworkAliases("mongo")
                .withExposedPorts(27017)
                .withEnv("MONGO_INITDB_DATABASE", "project-service")
                .withEnv("MONGO_USER", "user")
                .withEnv("MONGO_PASSWORD", "password");

        // Container setup for Discovery Server
        @Container
        private final static GenericContainer<?> discoveryServer = new GenericContainer<>(DockerImageName.parse("flaviamihaela/discovery-server:latest"))
                .withNetwork(network)
                .withNetworkAliases("discovery-server")
                .withExposedPorts(8761)
                .withEnv("SPRING_PROFILES_ACTIVE", "docker")
                .withEnv("spring.zipkin.base-url", "http://zipkin:9411")
                .withCommand("--auth")
                .dependsOn(zipkin);
        
        // Container setup for API Gateway
        @Container
        private final static GenericContainer<?> apiGateway = new GenericContainer<>(DockerImageName.parse("flaviamihaela/api-gateway:latest"))
                .withNetwork(network)
                .withNetworkAliases("api-gateway")
                .withExposedPorts(8080)
                .withEnv("SPRING_PROFILES_ACTIVE", "docker")
                .withEnv("eureka.client.serviceUrl.defaultZone", "http://discovery-server:8761/eureka")
                .withEnv("spring.zipkin.base-url", "http://zipkin:9411")
                .dependsOn(discoveryServer, zipkin)
                .withStartupTimeout(Duration.ofMinutes(5));

        // Container setup for Project Service
        @Container
        private final static GenericContainer<?> projectService = new GenericContainer<>(DockerImageName.parse("flaviamihaela/project-service:latest"))
                .withNetwork(network)
                .withNetworkAliases("project-service")
                .withEnv("SPRING_PROFILES_ACTIVE", "docker")
                .withEnv("spring.data.mongodb.uri", "mongodb://mongo:27017/project-service") // Use the alias and port defined for the Mongo container
                .withEnv("eureka.client.serviceUrl.defaultZone", "http://eureka:password@discovery-server:8761/eureka")
                .dependsOn(discoveryServer, zipkin, mongo, apiGateway);     
                
        // Method to set up test environment, executed before all tests
        @BeforeAll
        public static void setUp() {
                mongo.start(); // Explicitly start the MongoDB container
                
                // Delay to ensure containers fully started and ready
                try {
                Thread.sleep(60000); // Wait 60 seconds
                } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle thread interruption
                }
        }

         // Test method to validate API Gateway routing
        @Test
        public void testApiGatewayRouting() throws Exception {

                // Construct URL to the API Gateway
                URL url = new URL("http://" + apiGateway.getHost() + ":" + apiGateway.getMappedPort(8080) + "/api/project");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");  // Set request method to GET

                int status = con.getResponseCode(); // Get the response code from the HTTP request
                assertEquals(HttpURLConnection.HTTP_OK, status); // Assert response code is HTTP 200 OK

        }
}
