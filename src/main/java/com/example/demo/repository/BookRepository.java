package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface BookRepository extends ReactiveNeo4jRepository<Book, String> {

}

