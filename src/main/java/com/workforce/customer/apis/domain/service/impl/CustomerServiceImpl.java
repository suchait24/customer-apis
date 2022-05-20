package com.workforce.customer.apis.domain.service.impl;

import com.workforce.customer.apis.domain.exception.CustomerApisException;
import com.workforce.customer.apis.domain.model.criteria.CustomerFilterCriteria;
import com.workforce.customer.apis.domain.model.entity.Customer;
import com.workforce.customer.apis.domain.model.framework.CustomerDetailResponse;
import com.workforce.customer.apis.domain.repo.AddressRepository;
import com.workforce.customer.apis.domain.repo.ContactNumberRepository;
import com.workforce.customer.apis.domain.repo.CustomerDetailRepository;
import com.workforce.customer.apis.domain.repo.CustomerRepository;
import com.workforce.customer.apis.domain.service.CustomerService;
import com.workforce.customer.apis.infra.mapper.CustomerMapper;
import com.workforce.customer.apis.infra.model.CustomerRequest;
import com.workforce.customer.apis.infra.model.CustomerPageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ContactNumberRepository contactNumberRepository;
    private final AddressRepository addressRepository;
    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public UUID createCustomer(CustomerRequest customerRequest) {

        var customer = customerMapper.customerFrom(customerRequest.getCustomer());
        Customer customerResult;

        try {
            customerResult = customerRepository.save(customer);
            log.info("Customer created in DB.");
        } catch (DataAccessException dataAccessException) {
            log.error("Error occurred while inserting records in db.");
            throw new CustomerApisException("Error occurred while inserting records in db.", INTERNAL_SERVER_ERROR);
        }

        var contactNumber = customerMapper.contactNumberFrom(customerRequest.getContactNumber(), customerResult);
        var address = customerMapper.addressFrom(customerRequest.getAddress(), customerResult);

        try {

            contactNumberRepository.save(contactNumber);
            log.info("ContactNumber created in DB.");
            addressRepository.save(address);
            log.info("Address created in DB.");
        } catch (DataAccessException dataAccessException) {
            log.error("Error occurred while inserting records in db.");
            throw new CustomerApisException("Error occurred while inserting records in db.", INTERNAL_SERVER_ERROR);
        }

        return customerResult.getCustomerId();
    }

    @Override
    public CustomerPageable getCustomer(CustomerFilterCriteria customerFilterCriteria) {

        Long totalCount;
        List<Customer> customerList;
        try {

            totalCount = customerDetailRepository.retrieveCustomerCount(customerFilterCriteria);
            customerList =
                    customerDetailRepository.retrieveCustomers(customerFilterCriteria);
        } catch (DataAccessException dataAccessException) {
            log.error("Error occurred while getting records from db.");
            throw new CustomerApisException("Error occurred while getting records from db.", INTERNAL_SERVER_ERROR);
        }

        Pageable pageable = PageRequest.of(customerFilterCriteria.getPage(),
                customerFilterCriteria.getCount());

        Page<Customer> customerPages = new PageImpl<>(customerList, pageable, totalCount);

        List<CustomerDetailResponse> customerDetailResponses =
                customerList.stream()
                .map(customerMapper::fromCustomer).collect(Collectors.toList());

        return CustomerPageable.builder().customerList(customerDetailResponses)
                .totalCount(totalCount)
                .totalPages(customerPages.getTotalPages()).build();
    }

}
