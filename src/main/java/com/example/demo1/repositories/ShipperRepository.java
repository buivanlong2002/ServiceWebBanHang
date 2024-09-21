package com.example.demo1.repositories;

import com.example.demo1.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
    List<Shipper> findByStatus(Shipper.Status status);
}
