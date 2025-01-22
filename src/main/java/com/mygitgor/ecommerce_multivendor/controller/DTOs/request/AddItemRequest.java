package com.mygitgor.ecommerce_multivendor.controller.DTOs.request;

import lombok.Data;

@Data
public class AddItemRequest {
    private String size;
    private int quantity;
    private Long productId;
}
