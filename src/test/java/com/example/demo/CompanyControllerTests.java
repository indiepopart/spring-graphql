package com.example.demo;

import com.example.demo.controller.CompanyController;
import com.example.demo.domain.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@GraphQlTest(CompanyController.class)
public class CompanyControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void shouldGetCompany() {


        when(this.companyRepository.findById(123L))
                .thenReturn(Mono.just(new Company(
                        "1234",
                        "private",
                        "12345678",
                        "UK",
                        LocalDate.of(2020, 1, 1),
                        0,
                        "Test Company",
                        "active")));

        this.graphQlTester
                .documentName("companyDetails")
                .variable("id", "123")
                .execute()
                .path("companyById")
                .matchesJson("""
                    {
                        "id": null,
                        "SIC": "1234",
                        "name": "Test Company",
                        "status": "active",
                        "category": "private",
                        "companyNumber": "12345678",
                        "countryOfOrigin": "UK",
                        "incorporationDate": "2020-01-01",
                        "mortgagesOutstanding": 0                       
                    }
                """);
    }
}