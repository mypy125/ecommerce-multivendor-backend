package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
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

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User>createUserHandler(@RequestBody SignupRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
