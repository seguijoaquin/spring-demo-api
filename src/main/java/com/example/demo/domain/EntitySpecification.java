package com.example.demo.domain;

import com.example.demo.dto.EntitySearchRequestDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class EntitySpecification {

    private static final String ENTITY_FIELD_VALUE = "value";
    private static final String ENTITY_FIELD_DESCRIPTION = "description";

    public static Specification<Entity> getEntitiySpec(EntitySearchRequestDTO requestDTO) {
        Specification<Entity> specification = Stream.of(
                ofNullable(requestDTO.getValue()).map(EntitySpecification::getEntityByValue),
                ofNullable(requestDTO.getValue()).map(EntitySpecification::getEntityByDescription)
        )
                .map(o -> o.orElse(null))
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(emptySearch());
        return orderByValue(specification);
    }

    private static Specification<Entity> getEntityByValue(String s) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(ENTITY_FIELD_VALUE), s));
    }

    private static Specification<Entity> getEntityByDescription(String s) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(ENTITY_FIELD_DESCRIPTION), toStartsWithField(s));
    }

    private static String toStartsWithField(String name) {
        return name.concat("%");
    }

    private static Specification<Entity> emptySearch() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction();
    }

    private static Specification<Entity> orderByValue(Specification<Entity> spec) {
        return (root, criteriaQuery, criteriaBuilder) -> spec.toPredicate(root, criteriaQuery.orderBy(criteriaBuilder.asc(root.get(ENTITY_FIELD_VALUE))), criteriaBuilder);
    }
}
