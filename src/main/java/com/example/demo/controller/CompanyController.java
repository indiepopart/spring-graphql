package com.example.demo.controller;

import com.example.demo.repository.CompanyRepository;
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
    @QueryMapping
    public Mono<Company> companyByName(@Argument String name){
        return companyRepository.findByName(name);

    }
}
