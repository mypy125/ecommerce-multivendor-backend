package com.mygitgor.ecommerce_multivendor.api.DTOs.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String otp;
}
