package com.example.demo.repository;

import com.example.demo.domain.Company;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyRepository extends ReactiveNeo4jRepository<Company, Long> {

    Mono<Company> findByName(String name);

    @Query("MATCH (c:Company) WHERE (:Person {name: $name})-[:HAS_CONTROL]->(c) RETURN c")
    Flux<Company> findByControlledBy(String name);
}
