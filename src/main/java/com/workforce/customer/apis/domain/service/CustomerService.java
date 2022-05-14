package com.workforce.customer.apis.domain.service;

import com.workforce.customer.apis.domain.model.criteria.CustomerFilterCriteria;
import com.workforce.customer.apis.domain.model.framework.CustomerDetailResponse;
import com.workforce.customer.apis.infra.model.CustomerRequest;
import com.workforce.customer.apis.infra.model.CustomerPageable;

import java.util.UUID;

public interface CustomerService {

    UUID createCustomer(CustomerRequest customerRequest);
    CustomerPageable getCustomer(CustomerFilterCriteria customerFilterCriteria);
}
