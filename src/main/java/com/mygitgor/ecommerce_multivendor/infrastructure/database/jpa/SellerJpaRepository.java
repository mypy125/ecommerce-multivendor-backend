package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {
    SellerEntity findByEmail(String email);
    List<SellerEntity> findByAccountStatus(AccountStatus status);
}
