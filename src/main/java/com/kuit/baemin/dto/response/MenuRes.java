package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRes {

    private Long id;
    private Long restaurantId;
    private String name;
    private String description;
    private Long price;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .restaurantId(menu.getRestaurant().getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .status(menu.getStatus().toString())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .build();
    }
}
