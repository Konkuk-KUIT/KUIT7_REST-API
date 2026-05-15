package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Menu;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MenuRes {

    private Long id;
    private Long restaurantId;
    private String name;
    private Integer price;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .restaurantId(menu.getRestaurant().getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .status(menu.getStatus().name())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .build();
    }
}