package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardNewUpdateRequest {
    private String title;
    private String content;
    private String image_link;
    private String author;
    private String created;
}
