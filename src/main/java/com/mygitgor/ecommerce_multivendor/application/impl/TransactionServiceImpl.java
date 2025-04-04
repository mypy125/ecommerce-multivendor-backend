package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.api.exception.SellerException;
import com.mygitgor.ecommerce_multivendor.domain.model.Order;
import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.domain.model.Transaction;
import com.mygitgor.ecommerce_multivendor.domain.repository.SellerRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.TransactionRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.TransactionJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) throws SellerException {
        Seller seller = sellerRepository.getSellerById(order.getSellerId());

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        transaction.setDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
