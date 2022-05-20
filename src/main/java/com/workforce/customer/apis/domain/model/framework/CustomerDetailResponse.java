package com.workforce.customer.apis.domain.model.framework;

import com.workforce.customer.apis.domain.model.entity.Address;
import com.workforce.customer.apis.domain.model.entity.ContactNumber;
import com.workforce.customer.apis.infra.model.ContactNumberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CustomerDetailResponse {

    private UUID customerId;
    private String name;
    private List<ContactNumberResponse> contactNumberList;
    private List<AddressResponse> addressList;
    private OffsetDateTime createTime;
    private OffsetDateTime lastUpdatedTime;
}
