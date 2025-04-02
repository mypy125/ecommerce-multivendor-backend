package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.SellerReporterRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerReportJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.SellerReporterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SellerReporterRepositoryImpl implements SellerReporterRepository {
    private final SellerReportJpaRepository jpaRepository;
    private final SellerReporterMapper sellerReporterMapper;
}
