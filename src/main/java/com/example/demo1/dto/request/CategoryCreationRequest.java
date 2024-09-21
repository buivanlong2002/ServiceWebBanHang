package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryCreationRequest {
    private String name;
    private String parentId;
}
