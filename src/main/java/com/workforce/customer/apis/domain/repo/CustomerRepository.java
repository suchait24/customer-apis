package com.workforce.customer.apis.domain.repo;

import com.workforce.customer.apis.domain.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    Customer save(Customer customer);
}
