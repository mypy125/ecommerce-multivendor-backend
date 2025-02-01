package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Order;
import com.mygitgor.ecommerce_multivendor.domain.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(Users users, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean proceedPaymentOrder(
            PaymentOrder paymentOrder,String paymentId, String paymentLinkId
    ) throws RazorpayException;
    PaymentLink createRazorpayPaymentLink(
            Users users, Long amount, Long orderId
    ) throws RazorpayException;
    String createStripePaymentLink(
            Users users, Long amount, Long orderId
    ) throws StripeException;

}
