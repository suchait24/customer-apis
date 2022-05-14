package com.workforce.customer.apis.bootstrap;

import com.workforce.customer.apis.infra.repo.CustomerBaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.workforce.customer.apis"})
@EntityScan(basePackages = "com.workforce.customer.apis.domain.model.entity")
@EnableJpaRepositories(basePackages = "com.workforce.customer.apis.domain.repo", repositoryBaseClass = CustomerBaseRepositoryImpl.class)
@SpringBootApplication
public class CustomerApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApisApplication.class, args);
	}

}
