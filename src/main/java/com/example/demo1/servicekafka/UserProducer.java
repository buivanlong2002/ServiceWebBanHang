package com.example.demo1.servicekafka;

import com.example.demo1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {
    private static final String TOPIC = "user";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    public void sendMessage(String message, User user1) {
        kafkaTemplate.send(TOPIC, message);
    }
}
