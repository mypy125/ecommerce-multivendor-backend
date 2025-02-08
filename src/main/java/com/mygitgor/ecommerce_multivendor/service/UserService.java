package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.User;

public interface UserService {
    User findByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
