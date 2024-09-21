package com.example.demo1.repositories;

import com.example.demo1.models.Transactions;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    @Query(value = "select TRANSACTIONS_SEQ.nextval from dual", nativeQuery = true)
    Integer getNextSequenceValue();
    Transactions findByClientId(Integer clientId);
}
