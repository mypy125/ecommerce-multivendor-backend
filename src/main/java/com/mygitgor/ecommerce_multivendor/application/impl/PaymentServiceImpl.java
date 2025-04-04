package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.config.PaymentConfig;
import com.mygitgor.ecommerce_multivendor.domain.model.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.model.Order;
import com.mygitgor.ecommerce_multivendor.domain.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.PaymentOrderRepository;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentOrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.application.service.PaymentService;
import com.paypal.api.payments.*;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentOrderRepository paymentOrderRepository;
    private final OrderRepository orderRepository;
    private final PaymentConfig conf;


    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);

        return paymentOrderRepository.save(paymentOrder);
    }


    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {
        return paymentOrderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("Payment order not found"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentLinkId(paymentId);
        if (paymentOrder == null) {
            throw new Exception("Payment order not found with payment link id: " + paymentId);
        }
        return paymentOrder;
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws PayPalRESTException {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            APIContext apiContext = new APIContext(conf.getPaypalClientId(), conf.getPaypalClientSecret(), conf.getPaypalMode());
            Payment payment = Payment.get(apiContext, paymentId);

            if ("approved".equals(payment.getState())) {
                Set<Order> orders = paymentOrder.getOrders();
                for (Order order : orders) {
                    order.setPaymentStatus(PaymentStatus.COMPILED);
                    orderRepository.save(order);
                }
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            }
            paymentOrder.setStatus(PaymentOrderStatus.FAILED);
            paymentOrderRepository.save(paymentOrder);
            return false;
        }
        return false;
    }

    @Override
    public Payment createPaypalPaymentLink(User user, Long amount, Long orderId) throws PayPalRESTException {
        amount = amount / 100;

        Amount paypalAmount = new Amount();
        paypalAmount.setCurrency("USD");
        paypalAmount.setTotal(String.format("%.2f", amount / 100.0));

        Transaction transaction = new Transaction();
        transaction.setAmount(paypalAmount);
        transaction.setDescription("Payment for OrderEntity ID: " + orderId);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/payment-cancel/" + orderId);
        redirectUrls.setReturnUrl("http://localhost:3000/payment-success/" + orderId);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(Arrays.asList(transaction));
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(new APIContext(conf.getPaypalClientId(), conf.getPaypalClientSecret(), conf.getPaypalMode()));

        for (Links link : createdPayment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                System.out.println("Payment link: " + link.getHref());
                break;
            }
        }

        return createdPayment;
    }

    @Override
    public String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = conf.getStripeApiKey();
        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(
                        SessionCreateParams
                                .PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success/"+orderId)
                .setCancelUrl("http://localhost:3000/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("ecommerce shop")
                                        .build())
                                .build()
                        ).build()
                ).build();

        Session session = Session.create(params);
        return session.getPaymentLink();
    }
}
