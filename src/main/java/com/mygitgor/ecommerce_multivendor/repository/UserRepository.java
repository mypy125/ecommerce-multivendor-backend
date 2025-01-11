package com.mygitgor.ecommerce_multivendor.repository;

import com.mygitgor.ecommerce_multivendor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
