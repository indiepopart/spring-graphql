package com.example.demo.controller;

import com.example.demo.domain.Company;
import com.example.demo.domain.Person;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @QueryMapping
    public Mono<Company> companyById(@Argument Long id) {
        return companyRepository.findById(id);
    }


    @SchemaMapping
    public Flux<Company> controls(Person person) {
        return companyRepository.findByControlledBy(person.getName());
    }

    @QueryMapping
    public Flux<Company> companyList(@Argument Long page) {
        return companyRepository.findAll().skip((page - 1) * 10).take(10);
    }

    @QueryMapping
    public Mono<Long> companyCount() {
        return companyRepository.count();
    }

}
