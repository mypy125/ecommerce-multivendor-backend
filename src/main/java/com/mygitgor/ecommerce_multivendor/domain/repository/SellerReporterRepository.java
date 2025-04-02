package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.SellerReport;

public interface SellerReporterRepository {
    SellerReport getSellerReport(Seller seller);
    SellerReport saveSellerReport(SellerReport sellerReport);
}
