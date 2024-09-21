package com.example.demo1.repositories;

import com.example.demo1.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
