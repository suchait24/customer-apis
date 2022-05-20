package com.workforce.customer.apis.infra.model;

import com.workforce.customer.apis.domain.model.entity.Customer;
import com.workforce.customer.apis.domain.model.framework.CustomerDetailResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerPageable {

    private Long totalCount;
    private int totalPages;
    private List<CustomerDetailResponse> customerList;
}
