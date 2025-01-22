package com.mygitgor.ecommerce_multivendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.exception.ProductException;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setMrpPrice(1000);
        product.setSellingPrice(800);
        product.setDiscountPercent(20);
        product.setColor("Red");
        product.setSize("M");
        product.setImages(List.of("image1.jpg", "image2.jpg"));
    }

    @Test
    void getProductById_shouldReturnProduct() throws Exception {
        Mockito.when(productService.findProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.mrpPrice").value(1000))
                .andExpect(jsonPath("$.sellingPrice").value(800))
                .andExpect(jsonPath("$.discountPercent").value(20))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.size").value("M"))
                .andExpect(jsonPath("$.images.length()").value(2))
                .andExpect(jsonPath("$.images[0]").value("image1.jpg"));
    }

    @Test
    void getProductById_shouldReturn404WhenProductNotFound() throws Exception {
        Mockito.when(productService.findProductById(1L))
                .thenThrow(new ProductException("Product not found"));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchProduct_shouldReturnProducts() throws Exception {
        List<Product> products = Collections.singletonList(product);
        Mockito.when(productService.searchProduct(anyString())).thenReturn(products);

        mockMvc.perform(get("/products/search")
                        .param("query", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].mrpPrice").value(1000));
    }

    @Test
    void getAllProducts_shouldReturnPagedProducts() throws Exception {
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product),
                PageRequest.of(0, 10),
                1);
        Mockito.when(productService.getAllProducts(any(), any(), any(), any(), any(), any(),
                        any(), any(), any(), any()))
                .thenReturn(productPage);

        mockMvc.perform(get("/products")
                        .param("pageNumber", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].mrpPrice").value(1000));
    }
}

