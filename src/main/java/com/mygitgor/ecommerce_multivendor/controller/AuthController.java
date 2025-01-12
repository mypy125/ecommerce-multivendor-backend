package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody SignupRequest req){
        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }
}
