package com.example.demo1.ServicConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientId {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Integer getClientId() {
        return jdbcTemplate.queryForObject("select nextval('SEQUENCE_COMMON')", Integer.class);
    }
}
