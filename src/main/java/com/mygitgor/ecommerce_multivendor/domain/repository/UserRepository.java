package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.User;

public interface UserRepository {
    User findUserByEmail(String email) throws Exception;
}
