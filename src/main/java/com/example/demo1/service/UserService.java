package com.example.demo1.service;

import com.example.demo1.dto.request.UserCreationRequest;
import com.example.demo1.dto.request.UserUpdateRequest;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.models.Transactions;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;
    private static final String REDIS_KEY_PREFIX = "User:";

    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<User> CreateUser(UserCreationRequest request) {
//        if(userRepository.existsByUsername(request.getUsername()))
//            throw new AppException(ErrorCode.USER_EXISTED);
        userRepository.existsByUsername(request.getUsername());
        User user = userMapper.toUser(request);

        // ma hoa password user
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));



        return Collections.singletonList(userRepository.save(user));

    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }
//   public Optional<User> getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//   }

    public User updateUser(Integer id , UserUpdateRequest request) {
        User user = getUserById(id);
        userMapper.updateUser(user,request);
//
        return userRepository.save(user);
    }
    public User deleteUser(Integer id) {
        userRepository.deleteById(id);
        return null;
    }
    public User getAllUsernamesAndPasswords(String username) {
       User user = userRepository.findByUsername(username);
        if (user != null) {
            User result = new User();
            result.setUsername(user.getUsername());
            result.setPassword(user.getPassword());
            return result;
        }
        return null;
    }

    /// lưu đối tượng user vào redis
    public void saveUserToRedis(String key, User user) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + key, user);
        if (user != null) {
            // Save the user to the database
            userRepository.save(user);

            // Optionally, delete the user from Redis after saving to the database
//            redisTemplate.delete(REDIS_KEY_PREFIX + key);
        }
    }
    // get đói tượng user từ redis ra
    public User getUserFromRedis(String key) {
        // Retrieve the user from Redis
        return (User) redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + key);
    }

}
