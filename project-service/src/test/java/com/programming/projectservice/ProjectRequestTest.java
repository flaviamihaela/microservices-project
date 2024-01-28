//Declare package for class
package com.programming.projectservice;

//Imports
import com.programming.projectservice.dto.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectRequestTest {

    //Test verifies getters and setteres
    @Test
    void testProjectRequestGettersAndSetters() {
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setName(name);
        projectRequest.setDescription(description);

        assertEquals(name, projectRequest.getName(), "The getName method should return the correct name");
        assertEquals(description, projectRequest.getDescription(), "The getDescription method should return the correct description");
    }

    //Test verifies constructor
    @Test
    void testProjectRequestAllArgsConstructor() {
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectRequest projectRequest = new ProjectRequest(name, description);

        assertEquals(name, projectRequest.getName(), "Constructor should set the correct name");
        assertEquals(description, projectRequest.getDescription(), "Constructor should set the correct description");
    }

    //Test verifies builder methods
    @Test
    void testProjectRequestBuilder() {
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectRequest projectRequest = ProjectRequest.builder()
                                                      .name(name)
                                                      .description(description)
                                                      .build();

        assertEquals(name, projectRequest.getName(), "Builder should set the correct name");
        assertEquals(description, projectRequest.getDescription(), "Builder should set the correct description");
    }

    //Test verifies the equals and hashcode methods
    @Test
    void testProjectRequestEqualsAndHashCode() {
        ProjectRequest projectRequest1 = new ProjectRequest("Project1", "Description1");
        ProjectRequest projectRequest2 = new ProjectRequest("Project1", "Description1");
        ProjectRequest projectRequest3 = new ProjectRequest("Project2", "Description2");

        assertEquals(projectRequest1, projectRequest2, "Two project requests with the same data should be equal");
        assertNotEquals(projectRequest1, projectRequest3, "Two project requests with different data should not be equal");

        assertEquals(projectRequest1.hashCode(), projectRequest2.hashCode(), "Hash codes should be equal for equal objects");
        assertNotEquals(projectRequest1.hashCode(), projectRequest3.hashCode(), "Hash codes should not be equal for different objects");
    }

    //Test verifies tostring method
    @Test
    void testProjectRequestToString() {
        ProjectRequest projectRequest = new ProjectRequest("Project1", "Description1");
        String projectRequestString = projectRequest.toString();

        assertTrue(projectRequestString.contains("Project1"), "toString should include project name");
        assertTrue(projectRequestString.contains("Description1"), "toString should include project description");
    }
}
