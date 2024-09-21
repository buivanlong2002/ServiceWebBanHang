package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "BOARDNEW")
public class Boardnew {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "IMAGE_LINK")
    private String imageLink;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "CREATED")
    private String created;
}
