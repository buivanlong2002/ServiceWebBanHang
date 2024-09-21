package com.example.demo1.repositories;



import com.example.demo1.models.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository  extends JpaRepository<Vouchers, Integer> {
    Vouchers findByCodeContaining(String code);
}
