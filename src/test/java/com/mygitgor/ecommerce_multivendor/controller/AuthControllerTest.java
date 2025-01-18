package com.mygitgor.ecommerce_multivendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.LoginOtpRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUserHandler() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFullName("Test User");
        signupRequest.setOtp("123456");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt("mocked-jwt-token");
        authResponse.setMessage("register success");
        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        Mockito.when(authService.createUser(Mockito.any(SignupRequest.class))).thenReturn("mocked-jwt-token");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.message").value("register success"))
                .andExpect(jsonPath("$.role").value("ROLE_CUSTOMER"));
    }

    @Test
    void testSentOtpHandler() throws Exception {
        LoginOtpRequest loginOtpRequest = new LoginOtpRequest();
        loginOtpRequest.setEmail("test@example.com");
        loginOtpRequest.setRole(USER_ROLE.ROLE_CUSTOMER);

        Mockito.doNothing().when(authService).sentLoginOtp(Mockito.anyString(), Mockito.any(USER_ROLE.class));

        mockMvc.perform(post("/auth/sent/login-signup-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginOtpRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("otp sent successfully"));
    }

    @Test
    void testLoginHandler() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setOtp("123456");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt("mocked-jwt-token");
        authResponse.setMessage("Login success");
        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        Mockito.when(authService.signing(Mockito.any(LoginRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/auth/signing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.message").value("Login success"))
                .andExpect(jsonPath("$.role").value("ROLE_CUSTOMER"));
    }
}
