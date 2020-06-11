package com.example.demo.service;

import com.example.demo.domain.Entity;
import com.example.demo.domain.EntitySpecification;
import com.example.demo.dto.EntitySearchRequestDTO;
import com.example.demo.dto.PagedResponseDTO;
import com.example.demo.repository.EntitiesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class EntityService {

    private EntitiesRepository entitiesRepository;

    public EntityService(EntitiesRepository entitiesRepository) {
        this.entitiesRepository = entitiesRepository;
    }

    public PagedResponseDTO searchEntities(EntitySearchRequestDTO requestDTO, Pageable pageable) {

        Specification<Entity> specification = EntitySpecification.getEntitiySpec(requestDTO);

        Page<Entity> entities = this.entitiesRepository.findAll(specification, pageable);

        return PagedResponseDTO.fromPage(entities);
    }

    public Entity create(Entity entity) {
        return this.entitiesRepository.save(entity);
    }
}
