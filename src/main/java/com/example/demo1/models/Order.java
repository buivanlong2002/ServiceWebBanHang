package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "ORDER2")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Long id;                // ID đơn hàng (tự động tăng)
    @Column(name = "TRANSACTIONID")
    private int transactionId;     // Liên kết với ID của Transaction
    @Column(name = "USERNAME")
    private String userName;       // Tên người nhận hàng
    @Column(name = "USERMAIL")
    private String userMail;       // Email người nhận hàng
    @Column(name = "USERPHONE")
    private String userPhone;      // Số điện thoại người nhận hàng
    @Column(name = "ADDRESS")
    private String address;        // Địa chỉ giao hàng
    @Column(name = "AMOUNT")
    private double amount;     // Tổng số tiền đơn hàng
    @Column(name = "PAYMENT")
    private String payment;        // Phương thức thanh toán
    @Column(name = "MESSAGE")
    private String message;        // Ghi chú đơn hàng
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus status;  // Trạng thái đơn hàng (e.g., Processing, Shipped)
    @Column(name = "CREATED")
    private Date created; // Ngày tạo đơn hàng

}

