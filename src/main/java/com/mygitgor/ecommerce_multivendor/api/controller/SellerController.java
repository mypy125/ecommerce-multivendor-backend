package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.SellerReport;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.security.JwtProvider;
import com.mygitgor.ecommerce_multivendor.api.DTOs.request.LoginRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.AuthResponse;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.VerificationCodeEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.VerificationCodeJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.AuthService;
import com.mygitgor.ecommerce_multivendor.application.service.EmailService;
import com.mygitgor.ecommerce_multivendor.application.service.SellerReportService;
import com.mygitgor.ecommerce_multivendor.application.service.SellerService;
import com.mygitgor.ecommerce_multivendor.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final VerificationCodeJpaRepository verificationCodeRepository;
    private final SellerService sellerService;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>loginSeller(@RequestBody LoginRequest req) throws Exception
    {
        String email = req.getEmail();

        req.setEmail("seller_"+email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception
    {
        VerificationCodeEntity verificationCode = verificationCodeRepository.findByOtp(otp);
        if(verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp...");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception
    {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();

        VerificationCodeEntity verificationCode = new VerificationCodeEntity();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Ecommerce email verification code";
        String text = "Welcome Ecommerce, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(),
                verificationCode.getOtp(),subject,text+frontend_url);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller>getSellerById(@PathVariable Long id) throws SellerException
    {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller>getSellerByJwt(@RequestHeader("Authorization")
                                                    String jwt) throws Exception
    {
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport>getSellerReport(@RequestHeader("Authorization")
                                                    String jwt) throws Exception
    {
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Seller>>getAllSeller(@RequestParam(required = false)
                                                        AccountStatus status)
    {
        List<Seller>sellers=sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller>updateSeller(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody Seller seller) throws Exception
    {
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>updateSeller(@PathVariable Long id) throws Exception
    {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
