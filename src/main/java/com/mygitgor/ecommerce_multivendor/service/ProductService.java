package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateProductRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Seller;
import com.mygitgor.ecommerce_multivendor.exception.ProductException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest req, Seller seller) throws IllegalAccessException;
    void deleteProduct(Long productId) throws ProductException;
    Product updateProduct(Long productId, Product product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product> searchProduct(String query);
    Page<Product> getAllProducts(
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
    List<Product>getProductBySellerId(Long sellerId);
}
