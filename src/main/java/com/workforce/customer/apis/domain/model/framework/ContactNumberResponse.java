package com.workforce.customer.apis.domain.model.framework;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class ContactNumberResponse {

    private UUID contactUUID;
    private String contactNumber;
    private OffsetDateTime createTime;
    private OffsetDateTime lastUpdatedTime;

}
