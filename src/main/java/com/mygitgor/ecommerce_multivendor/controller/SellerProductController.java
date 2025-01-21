package com.mygitgor.ecommerce_multivendor.controller;


import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateProductRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Seller;
import com.mygitgor.ecommerce_multivendor.exception.ProductException;
import com.mygitgor.ecommerce_multivendor.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import com.mygitgor.ecommerce_multivendor.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers/products")
public class SellerProductController {
    private final SellerService sellerService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>>getProductBySellerId(@RequestHeader("Authorization")
                                                                 String jwt) throws Exception
    {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Product>products=productService.getProductBySellerId(seller.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product>createProduct(@RequestBody CreateProductRequest request,
                                                @RequestHeader("Authorization") String jwt) throws Exception
    {
        Seller seller = sellerService.getSellerProfile(jwt);
        Product product = productService.createProduct(request,seller);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long productId) throws Exception
    {
       try{
           productService.deleteProduct(productId);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (ProductException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product>updateProduct(@PathVariable Long productId,
                                                @RequestBody Product product) throws ProductException {
        Product updateProduct= productService.updateProduct(productId, product);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }
}
