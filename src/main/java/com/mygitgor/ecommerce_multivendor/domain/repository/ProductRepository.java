package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    void delete(Product product);
    Optional<Product> findById(Long productId);
    List<Product> searchProduct(String query);
    Page<Product> findAll(Specification<ProductEntity> spec, Pageable pageable);
    List<Product> findBySellerId(Long sellerId);
}
