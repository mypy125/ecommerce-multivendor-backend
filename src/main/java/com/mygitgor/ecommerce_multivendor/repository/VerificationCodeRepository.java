package com.mygitgor.ecommerce_multivendor.repository;

import com.mygitgor.ecommerce_multivendor.domain.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
    VerificationCode findByOtp(String otp);
}
