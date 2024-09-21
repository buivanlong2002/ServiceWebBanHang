package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewCreationRequest {

    private String name;
    private String email;
    private String productId;
    private String content;
    private String created;
}
