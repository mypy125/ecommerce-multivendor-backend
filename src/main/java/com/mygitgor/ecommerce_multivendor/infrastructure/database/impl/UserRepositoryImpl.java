package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.UserJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    @Override
    public User findUserByEmail(String email) throws Exception {
        UserEntity userEntity = jpaRepository.findByEmail(email);
        if (userEntity == null) {
            throw new Exception("User not found with email - " + email);
        }
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }
}
