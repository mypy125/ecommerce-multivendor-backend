package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;

import java.util.List;

public interface SellerService {
    SellerEntity getSellerProfile(String jwt) throws Exception;
    SellerEntity createSeller(SellerEntity seller) throws Exception;
    SellerEntity getSellerById(Long id) throws SellerException;
    SellerEntity getSellerByEmail(String email) throws Exception;
    List<SellerEntity> getAllSellers(AccountStatus status);
    SellerEntity updateSeller(Long id, SellerEntity seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    SellerEntity verifyEmail(String email, String otp) throws Exception;
    SellerEntity updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;

}
