package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.config.JwtProvider;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    @Override
    public String createUser(SignupRequest req) {
        User user = userRepository.findByEmail(req.getEmail());
        if(user==null){
            User createUser = new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createUser.setMobile("37444082124");
            createUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user = userRepository.save(createUser);

            Cart cart = new Cart();
            cart.setUser(user);

            cartRepository.save(cart);
        }
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(),null,authority);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }
}
