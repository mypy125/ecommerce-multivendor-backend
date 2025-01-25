package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Order;
import com.mygitgor.ecommerce_multivendor.domain.Transaction;

public interface TransactionService {
    Transaction createTransaction(Order order);
}
