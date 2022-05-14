package com.workforce.customer.apis.infra.controller;


import com.workforce.customer.apis.domain.model.criteria.CustomerFilterCriteria;
import com.workforce.customer.apis.domain.model.framework.CustomerDetailResponse;
import com.workforce.customer.apis.domain.service.CustomerService;
import com.workforce.customer.apis.infra.model.CustomerRequest;
import com.workforce.customer.apis.infra.model.CustomerPageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/workforce")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public UUID createCustomer(@RequestBody CustomerRequest customerRequest) {

        log.info("Customer request received : {} ", customerRequest);
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping
    public CustomerPageable getCustomers(
            @RequestParam(name = "customer-ids", required = false) List<UUID> customerIds,
            @RequestParam(name = "customer-names", required = false) List<String> customerNames,
            @RequestParam(name = "contact-numbers", required = false) List<String> contactNumbers,
            @RequestParam(name = "create-start-Date", required = false) Date createStartDate,
            @RequestParam(name = "create-end-Date", required = false) Date createEndDate,
            @RequestParam(name = "last-updated-start-date", required = false) Date lastUpdatedStartDate,
            @RequestParam(name = "last-updated-end-date", required = false) Date lastUpdatedEndDate,
            @RequestParam(name = "state" , required = false) List<String> states,
            @RequestParam(name = "city" , required = false) List<String> citys,
            @RequestParam(name = "country" , required = false) List<String> countries,
            @RequestParam(name = "pincode" , required = false) List<String> pincodes,
            @RequestParam(name = "sort-field", required = false) String sortField,
            @RequestParam(name = "sort-order",  required = false) String sortOrder,
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "count", required = false) int count) {

        CustomerFilterCriteria customerFilterCriteria =
                buildFilter(customerIds, customerNames, contactNumbers,createStartDate,createEndDate,
                        lastUpdatedStartDate,lastUpdatedEndDate,states,
                        citys,countries,pincodes,sortField,sortOrder, page, count);

        return customerService.getCustomer(customerFilterCriteria);
    }

    public CustomerFilterCriteria buildFilter(List<UUID> customerIds,
                                              List<String> customerNames,
                                              List<String> contactNumbers,
                                              Date createStartDate,
                                              Date createEndDate,
                                              Date lastUpdatedStartDate,
                                              Date lastUpdatedEndDate,
                                              List<String> states,
                                              List<String> citys,
                                              List<String> countries,
                                              List<String> pincodes,
                                              String sortField,
                                              String sortOrder,
                                              int page,
                                              int count) {

        return CustomerFilterCriteria.builder()
                .customerIds(customerIds)
                .customerNames(customerNames)
                .createStartDate(createStartDate)
                .createEndDate(createEndDate)
                .lastUpdatedStartDate(lastUpdatedStartDate)
                .lastUpdatedEndDate(lastUpdatedEndDate)
                .states(states)
                .citys(citys)
                .countries(countries)
                .pincodes(pincodes)
                .page(page)
                .count(count)
                .build();

    }

}
