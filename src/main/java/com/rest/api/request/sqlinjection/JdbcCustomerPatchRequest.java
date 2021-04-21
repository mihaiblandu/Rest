package com.rest.api.request.sqlinjection;

import lombok.Data;

@Data
public class JdbcCustomerPatchRequest {
    private String newFullName;
}