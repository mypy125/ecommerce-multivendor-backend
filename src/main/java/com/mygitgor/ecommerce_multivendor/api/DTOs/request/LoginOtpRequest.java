package com.mygitgor.ecommerce_multivendor.api.DTOs.request;

import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
