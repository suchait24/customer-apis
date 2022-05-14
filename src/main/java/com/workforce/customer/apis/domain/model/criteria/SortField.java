package com.workforce.customer.apis.domain.model.criteria;

import lombok.Getter;

@Getter
public enum SortField {

    CREATE_START_TIME("createTime"),
    LAST_UPDATE_TIME("lastupdateTime");

    SortField(String lastupdateTime) {
    }

}
