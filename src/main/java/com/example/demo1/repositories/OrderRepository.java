package com.example.demo1.repositories;

import com.example.demo1.models.Order;
import com.example.demo1.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}
