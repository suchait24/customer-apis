package com.workforce.customer.apis.domain.model.framework;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AddressResponse {

    private UUID addressUUID;
    private String street;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private String pincode;
    private OffsetDateTime createTime;
    private OffsetDateTime lastUpdatedTime;
}
