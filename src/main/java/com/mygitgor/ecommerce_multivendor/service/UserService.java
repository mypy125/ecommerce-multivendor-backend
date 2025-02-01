package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Users;

public interface UserService {
    Users findByJwtToken(String jwt) throws Exception;
    Users findUserByEmail(String email) throws Exception;
}
