package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdateRequest {
    private String catalogId;
    private String name;
    private String price;
    private Integer status;
    private String description;
    private String content;
    private String imageLink;
    private String created;
    private String discount;

}
