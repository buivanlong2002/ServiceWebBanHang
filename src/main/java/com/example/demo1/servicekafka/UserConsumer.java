package com.example.demo1.servicekafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    @KafkaListener(topics = "users")
    public void listen(String key , Object message) {
        System.out.println(message);
    }
}
