package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.VerificationCodeRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.VerificationCodeJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.VerificationCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VerificationCodeRepositoryImpl implements VerificationCodeRepository {
    private final VerificationCodeJpaRepository jpaRepository;
    private final VerificationCodeMapper verificationCodeMapper;
}
