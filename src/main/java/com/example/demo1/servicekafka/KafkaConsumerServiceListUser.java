package com.example.demo1.servicekafka;

import com.example.demo1.models.User;
import com.example.demo1.repositories.AdminRepository;
import com.example.demo1.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerServiceListUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KafkaProducerService kafkaProducer;
    @KafkaListener(topics = "request_users_topic")
    public void listen(String message) {
        System.out.println(message);
        if ("get_user".equals(message)) {
            List<User> users = userRepository.findAll();
            kafkaProducer.sendUsers(users);
        }
    }



}
