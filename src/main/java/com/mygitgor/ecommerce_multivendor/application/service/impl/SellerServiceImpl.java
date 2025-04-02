package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.security.JwtProvider;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.AddressEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.AddressJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerJpaRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressJpaRepository addressRepository;

    @Override
    public SellerEntity getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public SellerEntity createSeller(SellerEntity seller) throws Exception {
        SellerEntity sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if(sellerExist != null){
            throw new Exception("seller already exist, used different email");
        }
        AddressEntity savedAddress = addressRepository.save(seller.getPickupAddress());

        SellerEntity newSeller = new SellerEntity();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());

        return sellerRepository.save(newSeller);
    }

    @Override
    public SellerEntity getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new SellerException("seller not found with id "+id));
    }

    @Override
    public SellerEntity getSellerByEmail(String email) throws Exception {
        SellerEntity seller = sellerRepository.findByEmail(email);
        if(seller==null){
            throw new Exception("seller not found ...");
        }
        return seller;
    }

    @Override
    public List<SellerEntity> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public SellerEntity updateSeller(Long id, SellerEntity seller) throws Exception {
        SellerEntity existingSeller=this.getSellerById(id);

        if(seller.getSellerName() != null){
            existingSeller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile() != null){
            existingSeller.setMobile(seller.getMobile());
        }
        if(seller.getEmail() != null){
            existingSeller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails() != null
                && seller.getBusinessDetails().getBusinessName() != null)
        {
            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName()
            );
        }
        if(seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() != null
                && seller.getBankDetails().getIfscCode() != null
                && seller.getBankDetails().getAccountNumber() != null
        ){
            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName()
            );
            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber()
            );
            existingSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode()
            );
        }
        if(seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getMobile() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
        ){
            existingSeller.getPickupAddress()
                    .setAddress(seller.getPickupAddress().getAddress());
            existingSeller.getPickupAddress()
                    .setMobile(seller.getPickupAddress().getMobile());
            existingSeller.getPickupAddress()
                    .setCity(seller.getPickupAddress().getCity());
            existingSeller.getPickupAddress()
                    .setState(seller.getPickupAddress().getState());
        }
        if(seller.getGSTIN() != null){
            existingSeller.setGSTIN(seller.getGSTIN());
        }

        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        SellerEntity seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public SellerEntity verifyEmail(String email, String otp) throws Exception {
        SellerEntity seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public SellerEntity updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {
        SellerEntity seller = getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}
