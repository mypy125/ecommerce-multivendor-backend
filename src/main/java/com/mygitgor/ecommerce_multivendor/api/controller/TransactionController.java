package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.TransactionEntity;
import com.mygitgor.ecommerce_multivendor.application.service.SellerService;
import com.mygitgor.ecommerce_multivendor.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<TransactionEntity>>getTransactionBySeller(@RequestHeader("Authorization")
                                                                       String jwt) throws Exception
    {
        SellerEntity seller = sellerService.getSellerProfile(jwt);
        List<TransactionEntity>transactions = transactionService.getTransactionsBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionEntity>>getAllTransactions() throws Exception
    {
        List<TransactionEntity>transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
