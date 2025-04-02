package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerReportJpaRepository extends JpaRepository<SellerReportEntity, Long> {
    SellerReportEntity findBySellerId(Long sellerId);
}
