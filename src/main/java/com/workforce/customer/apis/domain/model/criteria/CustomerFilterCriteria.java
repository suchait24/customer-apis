package com.workforce.customer.apis.domain.model.criteria;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CustomerFilterCriteria {

    private List<UUID> customerIds;
    private List<String> customerNames;
    private List<String> contactNumbers;
    private Date createStartDate;
    private Date createEndDate;
    private Date lastUpdatedStartDate;
    private Date lastUpdatedEndDate;
    private List<String> citys;
    private List<String> countries;
    private List<String> pincodes;
    private List<String> states;
    private SortBy sortBy;
    private int page;
    private int count;

}
