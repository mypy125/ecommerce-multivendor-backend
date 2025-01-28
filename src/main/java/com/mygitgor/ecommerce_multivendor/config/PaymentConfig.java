package com.mygitgor.ecommerce_multivendor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "payment")
public class PaymentConfig {
    private String stripeApiKey;
    private String razorpayApiKey;
//    private String idramApiKey;
//    private String idramApiUrl;
//    private String easypayApiUrl;
//    private String easypayApiKey;
}
