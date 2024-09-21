package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedCreationRequest {
    private String productId;
    private String transactionId;
    private int qty;
}
