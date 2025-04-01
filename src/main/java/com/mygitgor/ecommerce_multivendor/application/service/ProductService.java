package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateProductRequest;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.SellerEntity;
import com.mygitgor.ecommerce_multivendor.api.exception.ProductException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductEntity createProduct(CreateProductRequest req, SellerEntity seller) throws IllegalAccessException;
    void deleteProduct(Long productId) throws ProductException;
    ProductEntity updateProduct(Long productId, ProductEntity product) throws ProductException;
    ProductEntity findProductById(Long productId) throws ProductException;
    List<ProductEntity> searchProduct(String query);
    Page<ProductEntity> getAllProducts(
            String category,
            String brand,
            String colors,
            String size,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );
    List<ProductEntity>getProductBySellerId(Long sellerId);
}
