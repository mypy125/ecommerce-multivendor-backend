package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.repository.ProductRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.ProductJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        return productMapper.toDomain(jpaRepository.save(productMapper.toEntity(product)));
    }

    @Override
    public void delete(Product product) {
        jpaRepository.delete(productMapper.toEntity(product));
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return jpaRepository.findById(productId)
                .map(productMapper::toDomain);
    }

    @Override
    public List<Product> searchProduct(String query) {
        return productMapper.toDomain(jpaRepository.searchProduct(query));
    }

    @Override
    public Page<Product> findAll(Specification<ProductEntity> spec, Pageable pageable) {
        return jpaRepository.findAll(spec, pageable)
                .map(productMapper::toDomain);
    }


    @Override
    public List<Product> findBySellerId(Long sellerId) {
        return productMapper.toDomain(jpaRepository.findBySellerId(sellerId));
    }
}
