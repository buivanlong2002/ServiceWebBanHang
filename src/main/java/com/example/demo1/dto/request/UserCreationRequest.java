package com.example.demo1.dto.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Setter
@Getter
public class UserCreationRequest {

    private String name;

    private String email;

    private String phone;

    private String username;

    private String password;
    private String created;



}
