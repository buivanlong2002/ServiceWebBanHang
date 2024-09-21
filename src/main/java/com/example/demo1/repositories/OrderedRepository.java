package com.example.demo1.repositories;

import com.example.demo1.models.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
