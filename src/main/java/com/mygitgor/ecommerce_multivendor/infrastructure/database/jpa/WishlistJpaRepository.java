package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistJpaRepository extends JpaRepository<WishlistEntity,Long> {
    Optional<WishlistEntity> findByUserId(Long userId);
}
