package com.mwambacodes.identityservice.utils;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operator = "=";
    private Object value;
    private String operation = "or";
}
