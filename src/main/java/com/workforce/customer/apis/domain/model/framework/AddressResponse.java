package com.workforce.customer.apis.domain.model.framework;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AddressResponse {

    private String street;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private String pincode;
    private OffsetDateTime createTime;
    private OffsetDateTime lastUpdatedTime;
}
