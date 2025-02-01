package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.*;
import com.mygitgor.ecommerce_multivendor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse>paymentSuccessHandler(@PathVariable String paymentId,
                                                            @RequestParam String paymentLinkId,
                                                            @RequestHeader("Authorization")
                                                                String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess = paymentService.proceedPaymentOrder(
                paymentOrder,paymentId,paymentLinkId
        );
        if(paymentSuccess){
            for(Order order: paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+ order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }
        }
        ApiResponse res = new ApiResponse();
        res.setMessage("payment success!");

       return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
