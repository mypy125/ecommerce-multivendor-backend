package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
