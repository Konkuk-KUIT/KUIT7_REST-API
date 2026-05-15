package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PageRes<T> {
    private List<T> content;
    private Integer totalPages;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;

    public static <T> PageRes<T> from (Page<T> page) {
        return PageRes.<T>builder()
                .content(page.getContent())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
