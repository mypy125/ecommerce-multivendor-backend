package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.domain.repository.SellerRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SellerRepositoryImpl implements SellerRepository {
    private final SellerJpaRepository jpaRepository;
    private final SellerMapper sellerMapper;

    @Override
    public Seller getSellerByEmail(String email) {
        SellerEntity sellerEntity = jpaRepository.findByEmail(email);
        return sellerMapper.toDomain(sellerEntity);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        SellerEntity sellerEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new SellerException("Seller not found with id " + id));
        return sellerMapper.toDomain(sellerEntity);
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return jpaRepository.findByAccountStatus(status)
                .stream()
                .map(sellerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Seller save(Seller seller) {
        SellerEntity sellerEntity = sellerMapper.toEntity(seller);
        SellerEntity savedEntity = jpaRepository.save(sellerEntity);
        return sellerMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Seller seller) {
        jpaRepository.delete(sellerMapper.toEntity(seller));
    }
}
