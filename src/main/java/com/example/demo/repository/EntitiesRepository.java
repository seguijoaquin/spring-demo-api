package com.example.demo.repository;

import com.example.demo.domain.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EntitiesRepository extends CrudRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {

    Page<Entity> findAll(Specification<Entity> spec, Pageable pageable);
}
