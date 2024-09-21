package com.example.demo1.repositories;

import com.example.demo1.models.Admin;
import com.example.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
   Admin findByUsername(String username);
    boolean existsByUsername(String username);
}
