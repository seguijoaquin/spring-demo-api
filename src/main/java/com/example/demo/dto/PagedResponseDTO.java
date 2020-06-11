package com.example.demo.dto;

import com.example.demo.domain.Entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PagedResponseDTO {
    private List<?> results;
    private PagingInfo paging;

    public static <T> PagedResponseDTO fromPage(Page<Entity> page) {
        long offset = page.getSize() * page.getNumber();
        PagingInfo pagingInfo = PagingInfo.builder()
                .offset(offset)
                .limit(page.getSize())
                .total(page.getTotalElements())
                .build();
        List<EntityResponseDTO> entityResponseDTOs = page.stream()
                .map(e -> EntityResponseDTO.builder()
                        .id(e.getId())
                        .value(e.getValue())
                        .description(e.getDescription())
                        .build())
                .collect(Collectors.toList());
        return PagedResponseDTO.builder()
                .paging(pagingInfo)
                .results(entityResponseDTOs)
                .build();
    }

    @Data
    @Builder
    public static class PagingInfo {
        private int limit;
        private long offset;
        private long total;
    }
}
