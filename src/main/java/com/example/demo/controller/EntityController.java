package com.example.demo.controller;


import com.example.demo.dto.EntityRequestDTO;
import com.example.demo.dto.EntitySearchRequestDTO;
import com.example.demo.service.EntityService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EntityController {

    private EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    public ResponseEntity<?> search(EntitySearchRequestDTO dto, Sort sort) {

        dto.setSort(sort);
        Pageable pageable = dto.toPageable();

        return ResponseEntity.ok(this.entityService.searchEntities(dto, pageable));
    }

    public ResponseEntity<?> create(EntityRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.entityService.create(dto.toEntity()));
    }
}
