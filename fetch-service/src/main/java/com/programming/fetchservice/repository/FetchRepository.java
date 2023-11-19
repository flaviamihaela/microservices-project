// Declare package for class
package com.programming.fetchservice.repository;

// Import classes and annotation
import com.programming.fetchservice.model.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface extends JpaRepository which provides CRUD operations for Fetch entity
public interface FetchRepository extends JpaRepository<Fetch, Long> {
}