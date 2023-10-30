// Define the package for this class
package com.programming.projectservice.repository;

// Import statements for required classes and annotations
import com.programming.projectservice.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

//<Project, String> - this repository will manage instances of Project
// and the type of the id field in the Project is String
public interface ProjectRepository extends MongoRepository<Project, String> {
}