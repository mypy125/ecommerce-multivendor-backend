package com.mygitgor.ecommerce_multivendor.controller.DTOs.response;

import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
