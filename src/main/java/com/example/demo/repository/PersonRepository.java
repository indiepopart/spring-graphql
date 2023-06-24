package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;


public interface PersonRepository extends ReactiveNeo4jRepository<Person, Long> {

    Mono<Person> findByName(String name);

}
