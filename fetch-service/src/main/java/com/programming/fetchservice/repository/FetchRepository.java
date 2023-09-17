package com.programming.fetchservice.repository;

import com.programming.fetchservice.model.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchRepository extends JpaRepository<Fetch, Long> {
}