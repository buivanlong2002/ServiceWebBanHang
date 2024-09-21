package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@ToString

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS")
    private String status ;
    @Column(name = "USER_SESSION")
    private String userSession;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_MAIL")
    private String userMail;
    @Column(name = "USER_PHONE")
    private String userPhone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "PAYMENT")
    private String payment;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "CREATED")
    private Date created;
    @Column(name = "CLIENT_ID")
    private Integer clientId;


}
