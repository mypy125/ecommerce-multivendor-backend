package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.PaymentLinkResponse;
import com.mygitgor.ecommerce_multivendor.domain.*;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentMethod;
import com.mygitgor.ecommerce_multivendor.repository.PaymentOrderRepository;
import com.mygitgor.ecommerce_multivendor.service.*;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    @PostMapping
    public ResponseEntity<PaymentLinkResponse>createOrderHandler(@RequestBody Address shippingAddress,
                                                                 @RequestParam PaymentMethod paymentMethod,
                                                                 @RequestHeader("Authorization")
                                                                    String jwt) throws Exception {
        Users users = userService.findByJwtToken(jwt);
        Cart cart = cartService.findUserCart(users);
        Set<Order>orders=orderService.createOrder(users,shippingAddress,cart);

        PaymentOrder paymentOrder = paymentService.createOrder(users,orders);

        PaymentLinkResponse res = new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment = paymentService.createRazorpayPaymentLink(
                    users, paymentOrder.getAmount(), paymentOrder.getId()
            );
            String paymentUrl=payment.get("short_url");
            String paymentId=payment.get("id");

            res.setPayment_link_url(paymentUrl);
            paymentOrder.setPaymentLinkId(paymentId);

            paymentOrderRepository.save(paymentOrder);
        }else {
            String paymentUrl=paymentService.createStripePaymentLink(
                    users, paymentOrder.getAmount(), paymentOrder.getId()
            );
            res.setPayment_link_url(paymentUrl);
        }

        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>>usersOrderHistoryHandler(@RequestHeader("Authorization")
                                                                  String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        List<Order>orders = orderService.usersOrderHistory(users.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order>getOrderById(@PathVariable Long orderId,
                                                   @RequestHeader("Authorization")
                                                   String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem>getOrderItemById(@PathVariable Long orderItemId,
                                                   @RequestHeader("Authorization")
                                                   String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        OrderItem orderItem = orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order>cancelOrder(@PathVariable Long orderId,
                                                   @RequestHeader("Authorization")
                                                   String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId, users);

        Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport report = sellerReportService.getSellerReport(seller);

        report.setCanceledOrders(report.getCanceledOrders()+1);
        report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);
        return ResponseEntity.ok(order);
    }

}
