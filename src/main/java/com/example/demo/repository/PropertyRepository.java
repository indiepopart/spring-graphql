package com.example.demo.repository;

import com.example.demo.domain.Property;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface PropertyRepository  extends ReactiveNeo4jRepository<Property, Long> {

}
