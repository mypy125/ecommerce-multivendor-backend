package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findBySellerId(Long sellerId);
    List<Transaction> findAll();
}
