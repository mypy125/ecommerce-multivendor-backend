package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeJpaRepository extends JpaRepository<VerificationCodeEntity, Long> {
    VerificationCodeEntity findByEmail(String email);
    VerificationCodeEntity findByOtp(String otp);
}
