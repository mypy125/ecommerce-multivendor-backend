package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Order;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Seller;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction>getTransactionsBySellerId(Seller seller);
    List<Transaction>getAllTransactions();
}
