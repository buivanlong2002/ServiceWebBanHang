package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCreationRequest {
    private String username;

    private String password;

    private String name;
}
