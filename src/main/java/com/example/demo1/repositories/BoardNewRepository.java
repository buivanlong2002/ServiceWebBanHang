package com.example.demo1.repositories;

import com.example.demo1.models.Boardnew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardNewRepository extends JpaRepository<Boardnew , Integer> {
}
