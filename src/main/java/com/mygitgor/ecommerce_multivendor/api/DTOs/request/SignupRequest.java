package com.mygitgor.ecommerce_multivendor.api.DTOs.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String fullName;
    private String otp;
}
