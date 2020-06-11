package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityResponseDTO {
    private long id;
    private String value;
    private String description;
}
