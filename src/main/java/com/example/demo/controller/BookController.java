package com.example.demo.controller;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @QueryMapping
    public Mono<Book> bookById(@Argument String id) {
        return bookRepository.findById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}