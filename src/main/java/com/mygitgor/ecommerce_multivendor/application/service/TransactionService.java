package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.TransactionEntity;

import java.util.List;

public interface TransactionService {
    TransactionEntity createTransaction(OrderEntity order);
    List<TransactionEntity>getTransactionsBySellerId(SellerEntity seller);
    List<TransactionEntity>getAllTransactions();
}
