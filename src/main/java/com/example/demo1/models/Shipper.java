package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Shipper")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="RECEIVING_STATUS")
    @Enumerated(EnumType.STRING)
    private ReceivingStatus receivingStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "ORDERCOUNT")
    private Integer orderCount ;

    // Getters and Setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = Status.ACTIVE; // Default status
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
    public enum ReceivingStatus {
        NOT_RECEIVED,          // Chưa nhận đơn hàng nào
        RECEIVED,              // Đã nhận được hàng
        ORDER_COMPLETED,       // Hoàn thành đơn hàng
        DELIVERY_FAILED,       // Giao hàng thất bại
        RETURN_COMPLETED       // Hoàn trả thành công
    }
}
