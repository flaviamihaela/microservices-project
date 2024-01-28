//Package for test class
package com.programming.projectservice;

// Imports 
import com.programming.projectservice.model.Project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectModelTest {

    // Test to verify Project model's getters and builder pattern 
    @Test
    void testProjectCreationAndAccessors() {
        // Creating Project instance with specified attributes
        String id = "id"; 
        String name = "Project Name"; 
        String description = "Project Description"; 
        Project project = Project.builder()
                .id(id) 
                .name(name) 
                .description(description) 
                .build(); 

        // Asserting getters return correct values
        assertEquals(id, project.getId(), "Checking if ID matches");
        assertEquals(name, project.getName(), "Checking if name matches");
        assertEquals(description, project.getDescription(), "Checking if description matches");
    }

    // Test to verify toString method includes all essential fields of Project model
    @Test
    void testProjectToString() {
        // Creating Project instance
        String id = "idA";
        String name = "Project A";
        String description = "Description A";
        Project project = new Project(id, name, description);

        // Getting string representation of project
        String projectString = project.toString();

        // Asserting string representation contains all necessary fields
        assertTrue(projectString.contains(id), "Checking if toString includes id");
        assertTrue(projectString.contains(name), "Checking if toString includes name");
        assertTrue(projectString.contains(description), "Checking if toString includes description");
    }

    // Test to verify setId method 
    @Test
    void testSetId() {
        // Creating Project instance and setting new ID
        Project project = new Project();
        String newId = "new-id";
        project.setId(newId);

        // Asserting ID was updated correctly
        assertEquals(newId, project.getId(), "Checking if setId updates the ID correctly");
    }

    // Test to verify setDescription
    @Test
    void testSetDescription() {
        // Creating Project instance and setting new ID
        Project project = new Project();
        String newDescription = "new-description";
        project.setDescription(newDescription);

        // Asserting ID was updated correctly
        assertEquals(newDescription, project.getDescription(), "Checking if setDescription updates the Description correctly");
    }


    // Test to verify setName method 
    @Test
    void testSetName() {
        // Creating Project instance and setting new ID
        Project project = new Project();
        String newName = "new-name";
        project.setName(newName);

        // Asserting ID was updated correctly
        assertEquals(newName, project.getName(), "Checking if setName updates the Name correctly");
    }

    // Test to verify equals method
    @Test
    void testEqualsMethod() {
        // Creating two Project instances with identical attributes
        Project project1 = new Project("id", "Project Name", "Description");
        Project project2 = new Project("id", "Project Name", "Description");

        // Asserting two projects with same attributes are considered equal
        assertEquals(project1, project2, "Checking if two projects with the same attributes are equal");
        // Asserting two projects with different IDs are not equal
        assertNotEquals(project1, new Project("id2", "Project Name", "Description"), "Checking if two projects with different IDs are not equal");
    }

    // Test to verify the hashCode method 
    @Test
    void testHashCodeMethod() {
        // Creating two Project instances with identical attributes
        Project project1 = new Project("id", "Project Name", "Description");
        Project project2 = new Project("id", "Project Name", "Description");

        // Asserting two projects with same data have same hashCode
        assertEquals(project1.hashCode(), project2.hashCode(), "Checking if hashCode is consistent for equal projects");
    }

    // Test for equals method with null and different class objects
    @Test
    void testEqualsSpecialCases() {
        Project project = new Project("id1", "Project1", "Description1");
        String notAProject = "Not a project";

        // Asserting that a project is not equal to null
        assertNotEquals(project, null, "A project should not be equal to null");
        // Asserting that a project is not equal to an object of a different class
        assertNotEquals(project, notAProject, "A project should not be equal to an object of a different type");
    }


}
