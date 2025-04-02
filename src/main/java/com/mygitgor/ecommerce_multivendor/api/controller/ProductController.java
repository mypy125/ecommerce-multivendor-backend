package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.api.exception.ProductException;
import com.mygitgor.ecommerce_multivendor.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductEntity>getProductById(@PathVariable Long productId) throws ProductException {
        try{
            ProductEntity product = productService.findProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>>searchProduct(@RequestParam(required = false) String query) throws ProductException {
        List<ProductEntity> products = productService.searchProduct(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductEntity>>getAllProducts(@RequestParam(required = false) String category,
                                                             @RequestParam(required = false) String brand,
                                                             @RequestParam(required = false) String color,
                                                             @RequestParam(required = false) String size,
                                                             @RequestParam(required = false) Integer minPrice,
                                                             @RequestParam(required = false) Integer maxPrice,
                                                             @RequestParam(required = false) Integer minDiscount,
                                                             @RequestParam(required = false) String sort,
                                                             @RequestParam(required = false) String stock,
                                                             @RequestParam(defaultValue = "0") Integer pageNumber
                                                       ) throws ProductException
    {
        return new ResponseEntity<>(productService.getAllProducts(
                category, brand, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber),
                HttpStatus.OK);
    }


}
