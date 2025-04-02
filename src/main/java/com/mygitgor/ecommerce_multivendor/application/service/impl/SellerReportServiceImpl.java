package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerReportJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private final SellerReportJpaRepository sellerReportRepository;

    @Override
    public SellerReportEntity getSellerReport(SellerEntity seller) {
        SellerReportEntity sellerReport = sellerReportRepository.findBySellerId(seller.getId());

        if(sellerReport==null){
            SellerReportEntity newReport = new SellerReportEntity();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }

        return sellerReport;
    }

    @Override
    public SellerReportEntity updateSellerReport(SellerReportEntity sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
