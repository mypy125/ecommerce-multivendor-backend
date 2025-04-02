package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.TransactionRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.TransactionJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final TransactionJpaRepository jpaRepository;
    private final TransactionMapper transactionMapper;
}
