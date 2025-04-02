package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;

public interface AuthService {
    String createUser(SignupRequest request) throws Exception;
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    AuthResponse signing(LoginRequest request) throws Exception;
}
