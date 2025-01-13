package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.AuthResponse;

public interface AuthService {
    String createUser(SignupRequest request) throws Exception;
    void sentLoginOtp(String email) throws Exception;
    AuthResponse signing(LoginRequest request);
}
