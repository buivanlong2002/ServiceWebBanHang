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
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CATALOG_ID")
    private String catalogId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private String price;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "IMAGE_LINK")
    private String imageLink;
    @Column(name = "CREATED")
    private String created;
    @Column(name = "DISCOUNT")
    private String discount;
}
