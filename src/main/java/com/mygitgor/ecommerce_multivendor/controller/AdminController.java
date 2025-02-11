package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.Seller;
import com.mygitgor.ecommerce_multivendor.domain.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final SellerService sellerService;

    @PostMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller>updateSellerStatus(@PathVariable Long id,
                                                    @PathVariable AccountStatus status) throws Exception
    {
        Seller updateSeller = sellerService.updateSellerAccountStatus(id,status);
        return ResponseEntity.ok(updateSeller);
    }
}
