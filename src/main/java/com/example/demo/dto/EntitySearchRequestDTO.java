package com.example.demo.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class EntitySearchRequestDTO {
    private static final int DEFAULT_LIMIT = 10;

    @Min(value = 0, message = "Query offset must be equal or greater than 0")
    private Long offset;

    @Min(value = 1, message = "Query results limit must be greater than 0")
    @Max(value = 100, message = "Query results can`t be larger than 100")
    private Integer limit;

    Sort sort;

    public EntitySearchRequestDTO() {
        this.limit = DEFAULT_LIMIT;
        this.offset = 0L;
    }

    String value;

    String description;

    public Pageable toPageable() {
        int pageNumber = (int) (this.offset / this.limit);
        return PageRequest.of(pageNumber, this.limit, this.sort);
    }
}
