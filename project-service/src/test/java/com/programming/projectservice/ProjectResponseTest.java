//Declare package for class
package com.programming.projectservice;
//Imports
import com.programming.projectservice.dto.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectResponseTest {

    //Test verifies the getters and setters
    @Test
    void testProjectResponseGettersAndSetters() {
        String id = "id123";
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(id);
        projectResponse.setName(name);
        projectResponse.setDescription(description);

        assertEquals(id, projectResponse.getId(), "The getId method should return the correct id");
        assertEquals(name, projectResponse.getName(), "The getName method should return the correct name");
        assertEquals(description, projectResponse.getDescription(), "The getDescription method should return the correct description");
    }

    //Test verifies the constructor
    @Test
    void testProjectResponseAllArgsConstructor() {
        String id = "id123";
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectResponse projectResponse = new ProjectResponse(id, name, description);

        assertEquals(id, projectResponse.getId(), "Constructor should set the correct id");
        assertEquals(name, projectResponse.getName(), "Constructor should set the correct name");
        assertEquals(description, projectResponse.getDescription(), "Constructor should set the correct description");
    }

    //Test verifies the builder methods
    @Test
    void testProjectResponseBuilder() {
        String id = "id123";
        String name = "Sample Project";
        String description = "Sample Description";

        ProjectResponse projectResponse = ProjectResponse.builder()
                                                        .id(id)
                                                        .name(name)
                                                        .description(description)
                                                        .build();

        assertEquals(id, projectResponse.getId(), "Builder should set the correct id");
        assertEquals(name, projectResponse.getName(), "Builder should set the correct name");
        assertEquals(description, projectResponse.getDescription(), "Builder should set the correct description");
    }

    //Test verifies the equals and hashcode methods
    @Test
    void testProjectResponseEqualsAndHashCode() {
        ProjectResponse projectResponse1 = new ProjectResponse("id123", "Project Name", "Description");
        ProjectResponse projectResponse2 = new ProjectResponse("id123", "Project Name", "Description");
        ProjectResponse projectResponse3 = new ProjectResponse("id124", "Project Name", "Description");

        assertEquals(projectResponse1, projectResponse2, "Two project responses with the same data should be equal");
        assertNotEquals(projectResponse1, projectResponse3, "Two project responses with different data should not be equal");

        assertEquals(projectResponse1.hashCode(), projectResponse2.hashCode(), "Hash codes should be equal for equal objects");
        assertNotEquals(projectResponse1.hashCode(), projectResponse3.hashCode(), "Hash codes should not be equal for different objects");
    }

    //Test verifies the tostring method
    @Test
    void testProjectResponseToString() {
        ProjectResponse projectResponse = new ProjectResponse("id123", "Project Name", "Description");
        String projectResponseString = projectResponse.toString();

        assertTrue(projectResponseString.contains("id123"), "toString should include id");
        assertTrue(projectResponseString.contains("Project Name"), "toString should include name");
        assertTrue(projectResponseString.contains("Description"), "toString should include description");
    }
}
