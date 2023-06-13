package com.example.demo.repository;

import com.example.demo.domain.Company;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface CompanyRepository extends ReactiveNeo4jRepository<Company, String> {

    Mono<Company> findByName(String name);
}
