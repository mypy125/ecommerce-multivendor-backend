package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.application.service.OrderService;
import com.mygitgor.ecommerce_multivendor.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/orders")
@RequiredArgsConstructor
public class SellerOrderController {
    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping("/user")
    public ResponseEntity<List<OrderEntity>> getAllOrdersHandler(@RequestHeader("Authorization")
                                                                String jwt) throws Exception
    {
        SellerEntity seller = sellerService.getSellerProfile(jwt);
        List<OrderEntity>orders = orderService.sellersOrder(seller.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<OrderEntity>updateOrderHandler(@PathVariable Long orderId,
                                                         @PathVariable OrderStatus orderStatus,
                                                         @RequestHeader("Authorization")
                                            String jwt) throws Exception
    {
        OrderEntity order = orderService.updateOrderStatus(orderId,orderStatus);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }
}
