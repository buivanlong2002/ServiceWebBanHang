package com.example.demo1.servicekafka;

import com.example.demo1.ServicConsumer.TransactionConsumer;
import com.example.demo1.models.Transactions;
import com.example.demo1.repositories.TransactionRepository;
import com.example.demo1.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaConsumerService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConsumer transactionConsumer;
    @Autowired
    private TransactionService transactionService;
    private final ObjectMapper objectMapper;
    private CompletableFuture<Transactions> transactionsFuture = new CompletableFuture<>();

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "kafka_test_topic")
    public  void listen(String message) {
        try {
            Transactions transactions = objectMapper.readValue(message, Transactions.class);

            Transactions transactions1 = transactionService.processTransaction(transactions);
            System.out.println(transactions1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
