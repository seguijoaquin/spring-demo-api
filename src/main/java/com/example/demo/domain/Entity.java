package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@javax.persistence.Entity
@Table(name = "entities")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String value;

    String description;
}
