package com.example.demo1.controller;

import com.example.demo1.service.BaseRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/redis")
@RequiredArgsConstructor
public class RedisController {

    private final BaseRedisService redisService;
    @PostMapping
    public void set(){
        redisService.set("hihi1","haha");
    }
    @GetMapping
    public void get(){
        redisService.get("user");
    }
}
