package com.mygitgor.ecommerce_multivendor.domain.model.details;

import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private String paymentId;
    private String paymentLinkId;
    private String paymentLinkReferenceId;
    private String paymentLinkStatus;
    private String paymentIdZWSP;
    private PaymentStatus status;

    public PaymentDetails(PaymentStatus status) {
        this.status = status;
    }
}
