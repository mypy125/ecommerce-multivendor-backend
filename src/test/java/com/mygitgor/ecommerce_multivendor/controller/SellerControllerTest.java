package com.mygitgor.ecommerce_multivendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygitgor.ecommerce_multivendor.api.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.VerificationCodeEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.VerificationCodeJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.AuthService;
import com.mygitgor.ecommerce_multivendor.application.service.EmailService;
import com.mygitgor.ecommerce_multivendor.application.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private SellerService sellerService;

    @MockitoBean
    private EmailService emailService;

    @MockitoBean
    private VerificationCodeJpaRepository verificationCodeRepository;

    private SellerEntity seller;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        seller = new SellerEntity();
        seller.setId(1L);
        seller.setEmail("test@test.com");
        seller.setSellerName("Test SellerEntity");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@test.com");
        loginRequest.setOtp("123456");
    }

    @Test
    void loginSeller_shouldReturnAuthResponse() throws Exception {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt("test-jwt-token");

        Mockito.when(authService.signing(any(LoginRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/sellers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("test-jwt-token"));
    }

    @Test
    void getSellerById_shouldReturnSeller() throws Exception {
        Mockito.when(sellerService.getSellerById(1L)).thenReturn(seller);

        mockMvc.perform(get("/sellers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.sellerName").value("Test SellerEntity"));
    }

    @Test
    void getAllSeller_shouldReturnListOfSellers() throws Exception {
        Mockito.when(sellerService.getAllSellers(null))
                .thenReturn(Collections.singletonList(seller));

        mockMvc.perform(get("/sellers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void createSeller_shouldReturnCreatedSeller() throws Exception {
        VerificationCodeEntity verificationCode = new VerificationCodeEntity();
        verificationCode.setOtp("123456");
        verificationCode.setEmail("test@test.com");

        Mockito.when(sellerService.createSeller(any(SellerEntity.class))).thenReturn(seller);
        Mockito.when(verificationCodeRepository.save(any(VerificationCodeEntity.class))).thenReturn(verificationCode);

        mockMvc.perform(post("/sellers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seller)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }
}

