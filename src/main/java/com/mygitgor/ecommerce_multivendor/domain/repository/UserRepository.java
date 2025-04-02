package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.User;

public interface UserRepository {
    User findUserByEmail(String email) throws Exception;
}
