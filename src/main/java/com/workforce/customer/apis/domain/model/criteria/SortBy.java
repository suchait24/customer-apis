package com.workforce.customer.apis.domain.model.criteria;

import lombok.Data;


@Data
public class SortBy {

    private SortField sortField;
    private SortFieldOrder sortOrder;
}
