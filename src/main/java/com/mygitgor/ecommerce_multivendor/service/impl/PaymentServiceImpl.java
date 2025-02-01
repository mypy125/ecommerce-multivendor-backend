package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.config.PaymentConfig;
import com.mygitgor.ecommerce_multivendor.domain.Order;
import com.mygitgor.ecommerce_multivendor.domain.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentOrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.repository.PaymentOrderRepository;
import com.mygitgor.ecommerce_multivendor.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentOrderRepository paymentOrderRepository;
    private final OrderRepository orderRepository;
    private final PaymentConfig conf;


    @Override
    public PaymentOrder createOrder(Users users, Set<Order> orders) {
        Long amount=orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder= new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setUsers(users);
        paymentOrder.setOrders(orders);

        return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {
        return paymentOrderRepository.findById(orderId)
                .orElseThrow(()-> new Exception("payment order not found"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception {
        PaymentOrder paymentOrder= paymentOrderRepository.findByPaymentLinkId(orderId);
        if(paymentOrder==null){
            throw new Exception("payment order not found with payment link id"+orderId);
        }
        return paymentOrder;
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder,
                                       String paymentId,
                                       String paymentLinkId) throws RazorpayException
    {
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            RazorpayClient razorpay = new RazorpayClient(conf.getRazorpayApiKey(), conf.getRazorpayApiKey());
            Payment payment = razorpay.payments.fetch(paymentId);

            String status= payment.get("status");
            if(status.equals("captured")){
                Set<Order>orders = paymentOrder.getOrders();
                for(Order order: orders){
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
    public PaymentLink createRazorpayPaymentLink(Users users, Long amount, Long orderId) throws RazorpayException {
        amount=amount*100;
        try{
            RazorpayClient razorpay = new RazorpayClient(conf.getRazorpayApiKey(), conf.getRazorpayApiKey());

            JSONObject paymentLinkRequest = getJsonObject(users, amount, orderId);

            PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkUrl= paymentLink.get("short_url");
            String paymentLinkId = paymentLink.get("id");

            return paymentLink;

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RazorpayException(e.getMessage());
        }
    }

    private static JSONObject getJsonObject(Users users, Long amount, Long orderId) {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency","USD");

        JSONObject customer = new JSONObject();
        customer.put("name", users.getFullName());
        customer.put("email", users.getEmail());
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify",notify);

        paymentLinkRequest.put("callback_url","http://localhost:3000/payment-success/"+ orderId);
        paymentLinkRequest.put("callback_method","get");
        return paymentLinkRequest;
    }

    @Override
    public String createStripePaymentLink(Users users, Long amount, Long orderId) throws StripeException {
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
