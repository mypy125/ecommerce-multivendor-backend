package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.Order;
import com.mygitgor.ecommerce_multivendor.domain.Seller;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.service.OrderService;
import com.mygitgor.ecommerce_multivendor.service.SellerService;
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
    public ResponseEntity<List<Order>> getAllOrdersHandler(@RequestHeader("Authorization")
                                                                String jwt) throws Exception
    {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Order>orders = orderService.sellersOrder(seller.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order>updateOrderHandler(@PathVariable Long orderId,
                                            @PathVariable OrderStatus orderStatus,
                                            @RequestHeader("Authorization")
                                            String jwt) throws Exception
    {
        Order order = orderService.updateOrderStatus(orderId,orderStatus);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }
}
