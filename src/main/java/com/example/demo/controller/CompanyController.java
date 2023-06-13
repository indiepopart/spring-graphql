package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.example.demo.domain.Company;
import reactor.core.publisher.Mono;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    @QueryMapping
    public Mono<Company> companyByName(@Argument String name) {
        return companyRepository.findByName(name);

    }

    @QueryMapping
    public Mono<Person> personByName(@Argument String name) {
        return personRepository.findByName(name);
    }
}
