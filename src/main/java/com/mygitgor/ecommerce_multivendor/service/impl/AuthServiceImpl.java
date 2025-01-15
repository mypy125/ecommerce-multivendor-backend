package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.config.JwtProvider;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.SignupRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.VerificationCode;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.repository.UserRepository;
import com.mygitgor.ecommerce_multivendor.repository.VerificationCodeRepository;
import com.mygitgor.ecommerce_multivendor.service.AuthService;
import com.mygitgor.ecommerce_multivendor.service.EmailService;
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
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;


    @Override
    public String createUser(SignupRequest req) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong otp...");
        }

        User user = userRepository.findByEmail(req.getEmail());
        if(user==null){
            User createUser = new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createUser.setMobile("37444******");
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

    @Override
    public void sentLoginOtp(String email) throws Exception {
        String SINGING_PREFIX="signin_";

        if(email.startsWith(SINGING_PREFIX)){
            email = email.substring(SINGING_PREFIX.length());

            User user = userRepository.findByEmail(email);
            if(user ==null){
                throw new Exception("user not exist with provided email");
            }
        }
        VerificationCode isExist= verificationCodeRepository.findByEmail(email);
        if(isExist != null){
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "login/signup otp";
        String text = "your login/signup otp is - "+otp;

        emailService.sendVerificationOtpEmail(email,otp,subject,text);
    }

    @Override
    public AuthResponse signing(LoginRequest request) {
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

    private Authentication authenticate(String username, String otp){
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
        if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new BadCredentialsException("wrong otp");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
