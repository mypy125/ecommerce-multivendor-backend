package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;

import java.util.List;

public interface TransactionService {
    TransactionEntity createTransaction(OrderEntity order);
    List<TransactionEntity>getTransactionsBySellerId(SellerEntity seller);
    List<TransactionEntity>getAllTransactions();
}
