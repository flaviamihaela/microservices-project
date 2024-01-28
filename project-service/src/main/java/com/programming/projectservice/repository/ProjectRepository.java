// Define the package for class
package com.programming.projectservice.repository;

import com.google.common.base.Optional;
// Import classes and annotations
import com.programming.projectservice.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

//<Project, String> - this repository will manage instances of Project
// and the type of the id field in the Project is String
public interface ProjectRepository extends MongoRepository<Project, String> {
    // Interface extending MongoRepository - most of the CRUD operations provided
    Optional<Project> findByName(String name);
}