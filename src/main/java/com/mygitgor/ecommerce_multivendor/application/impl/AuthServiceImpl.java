package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.security.JwtProvider;
import com.mygitgor.ecommerce_multivendor.api.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.VerificationCodeEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.UserJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.VerificationCodeJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.AuthService;
import com.mygitgor.ecommerce_multivendor.application.service.EmailService;
import com.mygitgor.ecommerce_multivendor.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository userRepository;
    private final CartJpaRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final VerificationCodeJpaRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerJpaRepository sellerRepository;


    @Override
    public String createUser(SignupRequest req) throws Exception {
        VerificationCodeEntity verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong otp...");
        }

        UserEntity user = userRepository.findByEmail(req.getEmail());
        if(user ==null){
            UserEntity createUser = new UserEntity();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createUser.setMobile("37444******");
            createUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user = userRepository.save(createUser);

            CartEntity cart = new CartEntity();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(),null,authority);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public void sentLoginOtp(String email, USER_ROLE role) throws Exception {
        String SINGING_PREFIX="signing_";

        if(email.startsWith(SINGING_PREFIX)){
            email = email.substring(SINGING_PREFIX.length());

            if(role.equals(USER_ROLE.ROLE_SELLER)){
                SellerEntity seller = sellerRepository.findByEmail(email);
                if(seller==null){
                    throw new Exception("seller not exist with provided email");
                }
            }else {
                UserEntity user = userRepository.findByEmail(email);
                if(user ==null){
                    throw new Exception("user not exist with provided email");
                }

            }

        }
        VerificationCodeEntity isExist= verificationCodeRepository.findByEmail(email);
        if(isExist != null){
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCodeEntity verificationCode = new VerificationCodeEntity();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "login/signup otp";
        String text = "your login/signup otp is - "+otp;

        emailService.sendVerificationOtpEmail(email,otp,subject,text);
    }

    @Override
    public AuthResponse signing(LoginRequest request) throws Exception {
        String username = request.getEmail();
        String otp= request.getOtp();

        Authentication authentication = authenticate(username,otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login success");

        Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
        String roleName= authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) throws Exception {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        String SELLER_PREFIX="seller_";
        if(username.startsWith(SELLER_PREFIX)){
           username=username.substring(SELLER_PREFIX.length());
        }

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }

        VerificationCodeEntity verificationCode = verificationCodeRepository.findByEmail(username);
        if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
