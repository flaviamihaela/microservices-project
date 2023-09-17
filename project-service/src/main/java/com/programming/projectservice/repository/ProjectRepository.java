package com.programming.projectservice.repository;

import com.programming.projectservice.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}