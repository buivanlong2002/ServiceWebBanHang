package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Product product;
    private int qty;
    private double price;
}
