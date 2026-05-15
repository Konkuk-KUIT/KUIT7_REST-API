package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRes {

    private Long id;
    private String name;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CategoryRes from(Category category) {
        return CategoryRes.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus().toString())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
