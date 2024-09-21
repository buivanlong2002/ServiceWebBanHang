package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewUpdateRequest {
    private String name;
    private String email;
    private String productId;
    private String content;
}
