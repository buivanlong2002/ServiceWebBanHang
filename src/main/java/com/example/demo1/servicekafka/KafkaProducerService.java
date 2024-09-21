package com.example.demo1.servicekafka;
import com.example.demo1.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaProducerService {
    private static final String TOPIC ="user_topic";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendUsers(List<User> users) {
        try {
           String json = objectMapper.writeValueAsString(users);
           System.out.println(json);
           kafkaTemplate.send(TOPIC, json);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
