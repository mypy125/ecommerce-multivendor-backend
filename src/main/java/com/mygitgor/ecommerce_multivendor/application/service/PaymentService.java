package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.PaymentOrderEntity;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrderEntity createOrder(User user, Set<OrderEntity> orders);
    PaymentOrderEntity getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrderEntity getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean proceedPaymentOrder(
            PaymentOrderEntity paymentOrder, String paymentId, String paymentLinkId
    )throws PayPalRESTException;
    Payment createPaypalPaymentLink(
            User user, Long amount, Long orderId
    )throws PayPalRESTException;
    String createStripePaymentLink(
            User user, Long amount, Long orderId
    ) throws StripeException;

}
