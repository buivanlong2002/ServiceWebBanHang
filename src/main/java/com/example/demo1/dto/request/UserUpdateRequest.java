package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class UserUpdateRequest {
//    private String name;

    private String email;

    private String phone;

    private String username;

    private String password;


}
