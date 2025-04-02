package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;

import java.util.List;

public interface SellerRepository {
    Seller getSellerByEmail(String email);
    Seller getSellerById(Long id) throws SellerException;
    List<Seller> getAllSellers(AccountStatus status);
    Seller save(Seller seller);
    void delete(Seller seller);
}
