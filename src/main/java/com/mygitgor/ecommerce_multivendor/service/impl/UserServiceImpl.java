package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.config.JwtProvider;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Users findByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.findUserByEmail(email);
    }

    @Override
    public Users findUserByEmail(String email) throws Exception {
        Users users = userRepository.findByEmail(email);
        if(users ==null){
            throw new Exception("user not found with email - "+email);
        }
        return users;
    }
}
