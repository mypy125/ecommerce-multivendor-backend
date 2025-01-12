package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest request);
}
