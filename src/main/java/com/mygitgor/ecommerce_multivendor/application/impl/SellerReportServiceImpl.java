package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.SellerReport;
import com.mygitgor.ecommerce_multivendor.domain.repository.SellerReporterRepository;
import com.mygitgor.ecommerce_multivendor.application.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private final SellerReporterRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        return sellerReportRepository.getSellerReport(seller);
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.saveSellerReport(sellerReport);
    }
}
