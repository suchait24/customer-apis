package com.workforce.customer.apis.domain.repo;

import com.workforce.customer.apis.domain.model.criteria.CustomerFilterCriteria;
import com.workforce.customer.apis.domain.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerDetailRepository extends CrudRepository<Customer, UUID> {

    Long retrieveCustomerCount(CustomerFilterCriteria customerFilterCriteria);
    List<Customer> retrieveCustomers(CustomerFilterCriteria customerFilterCriteria);
}
