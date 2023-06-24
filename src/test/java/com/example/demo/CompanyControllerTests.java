package com.example.demo;

import com.example.demo.controller.CompanyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(CompanyController.class)
public class CompanyControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    //@Test
    void shouldGetCompany() {
        this.graphQlTester
                .documentName("companyDetails")
                .variable("id", "123")
                .execute()
                .path("companyById")
                .matchesJson("""
                    {
                        "id": "123",
                        "SIC": "1234",
                        "name": "Test Company",
                        "status": "active",
                        "category": "private",
                        "companyNumber": "12345678",
                        "countryOfOrigin": "UK",
                        "incorporationDate": "2020-01-01",
                        "mortgagesOutstanding": 0,                        
                        
                        }
                    }
                """);
    }
}