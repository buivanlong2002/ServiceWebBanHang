package com.example.demo1.repositories;

import com.example.demo1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByCatalogId(String catalogId);

}
