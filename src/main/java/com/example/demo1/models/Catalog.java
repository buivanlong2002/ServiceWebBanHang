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
@Table(name = "CATALOG")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
    @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PARENT_ID")
    private String parentId;
}
