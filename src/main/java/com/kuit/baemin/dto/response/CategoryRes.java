package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.category.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRes {

    private Long id;
    private String name;

    public static CategoryRes from(Category category) {
        return CategoryRes.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
