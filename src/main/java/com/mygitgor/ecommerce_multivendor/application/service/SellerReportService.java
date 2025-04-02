package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;

public interface SellerReportService {
    SellerReportEntity getSellerReport(SellerEntity seller);
    SellerReportEntity updateSellerReport(SellerReportEntity sellerReport);
}
