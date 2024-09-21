package com.example.demo1.repositories;

import com.example.demo1.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review ,Integer> {
}
