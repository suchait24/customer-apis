package com.workforce.customer.apis.bootstrap;

import com.workforce.customer.apis.domain.repo.AddressRepository;
import com.workforce.customer.apis.domain.repo.ContactNumberRepository;
import com.workforce.customer.apis.domain.repo.CustomerDetailRepository;
import com.workforce.customer.apis.domain.repo.CustomerRepository;
import com.workforce.customer.apis.domain.service.CustomerService;
import com.workforce.customer.apis.domain.service.impl.CustomerServiceImpl;
import com.workforce.customer.apis.infra.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerServiceImpl getCustomerServiceImpl(CustomerRepository customerRepository, ContactNumberRepository contactNumberRepository,
                                                  AddressRepository addressRepository,
                                                  CustomerDetailRepository customerDetailRepository,
                                                  CustomerMapper customerMapper) {
        return new CustomerServiceImpl(customerRepository, contactNumberRepository, addressRepository, customerDetailRepository, customerMapper);
    }

    @Bean
    public CustomerMapper getCustomerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }
}
