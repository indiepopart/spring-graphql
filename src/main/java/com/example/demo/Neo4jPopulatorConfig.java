package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
@Configuration
public class Neo4jPopulatorConfig {


    @Bean //Not working in reactive
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        //factory.setResources(new Resource[]{new ClassPathResource("seed/books.json")});
        return factory;
    }

}
