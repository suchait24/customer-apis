package com.workforce.customer.apis.infra.model;


import lombok.Data;

//TODO: perform validations

@Data
public class CustomerRequest {

    private CustomerDTO customer;
    private ContactNumberDTO contactNumber;
    private AddressDTO address;
}
