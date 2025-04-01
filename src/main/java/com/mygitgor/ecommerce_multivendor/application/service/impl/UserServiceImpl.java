package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.security.JwtProvider;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.UserJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public UserEntity findByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.findUserByEmail(email);
    }

    @Override
    public UserEntity findUserByEmail(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email);
        if(user ==null){
            throw new Exception("user not found with email - "+email);
        }
        return user;
    }
}
