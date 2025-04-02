package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.SellerReport;
import com.mygitgor.ecommerce_multivendor.domain.repository.SellerReporterRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerReportJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.SellerReporterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SellerReporterRepositoryImpl implements SellerReporterRepository {
    private final SellerReportJpaRepository jpaRepository;
    private final SellerReporterMapper sellerReporterMapper;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReportEntity sellerReportEntity = jpaRepository.findBySellerId(seller.getId());
        if (sellerReportEntity == null) {
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return saveSellerReport(newReport);
        }
        return sellerReporterMapper.toDomain(sellerReportEntity);
    }

    @Override
    public SellerReport saveSellerReport(SellerReport sellerReport) {
        SellerReportEntity entity = sellerReporterMapper.toEntity(sellerReport);
        SellerReportEntity savedEntity = jpaRepository.save(entity);
        return sellerReporterMapper.toDomain(savedEntity);
    }

}
