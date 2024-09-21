package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TransactionCreationRequest {
    private Integer id;
    private String status;
    private String userSession;
    private String userName;
    private String userMail;
    private String userPhone;
    private String address;
    private String amount;
    private String payment;
    private String message;
    private Date created;
}
