package com.workforce.customer.apis.infra.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddressDTO {

    private String street;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private String pincode;
}
