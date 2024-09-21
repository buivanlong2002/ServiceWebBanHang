package com.example.demo1.ServicConsumer;

import com.example.demo1.servicekafka.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

    private KafkaConsumerService kafkaConsumerService;
}
