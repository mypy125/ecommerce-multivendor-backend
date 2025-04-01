package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;

public interface UserService {
    UserEntity findByJwtToken(String jwt) throws Exception;
    UserEntity findUserByEmail(String email) throws Exception;
}
