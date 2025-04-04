package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.domain.model.Order;
import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.Transaction;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order) throws SellerException;
    List<Transaction>getTransactionsBySellerId(Seller seller);
    List<Transaction>getAllTransactions();
}
