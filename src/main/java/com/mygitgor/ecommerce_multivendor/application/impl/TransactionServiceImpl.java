package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.TransactionJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionJpaRepository transactionRepository;
    private final SellerJpaRepository sellerRepository;

    @Override
    public TransactionEntity createTransaction(OrderEntity order) {
        SellerEntity seller = sellerRepository.findById(order.getSellerId()).get();

        TransactionEntity transaction = new TransactionEntity();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionEntity> getTransactionsBySellerId(SellerEntity seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
