package com.mygitgor.ecommerce_multivendor.controller.DTOs.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String fullName;
    private String otp;

}
