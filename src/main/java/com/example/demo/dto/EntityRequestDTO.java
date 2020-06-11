package com.example.demo.dto;

import com.example.demo.domain.Entity;
import lombok.Data;

@Data
public class EntityRequestDTO {

    private String value;
    private String description;

    public Entity toEntity() {
        return Entity.builder()
                .value(this.value)
                .description(this.description)
                .build();
    }
}
