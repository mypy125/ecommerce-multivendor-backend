package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Order;
import com.mygitgor.ecommerce_multivendor.domain.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean proceedPaymentOrder(
            PaymentOrder paymentOrder,String paymentId, String paymentLinkId
    )throws PayPalRESTException;
    Payment createPaypalPaymentLink(
            User user, Long amount, Long orderId
    )throws PayPalRESTException;
    String createStripePaymentLink(
            User user, Long amount, Long orderId
    ) throws StripeException;

}
