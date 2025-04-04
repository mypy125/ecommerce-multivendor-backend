package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Transaction;
import com.mygitgor.ecommerce_multivendor.domain.repository.TransactionRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.TransactionJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final TransactionJpaRepository jpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        return transactionMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<Transaction> findBySellerId(Long sellerId) {
        return transactionMapper.toDomain(jpaRepository.findBySellerId(sellerId));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionMapper.toDomain(jpaRepository.findAll());
    }
}
