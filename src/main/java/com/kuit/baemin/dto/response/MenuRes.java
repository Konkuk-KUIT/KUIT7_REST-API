package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MenuRes {

    private Long id;
    private String restaurantName;
    private int price;
    private String category;
    private MenuStatus status;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .restaurantName(menu.getRestaurant().getName())
                .price(menu.getPrice())
                .category(menu.getCategory())
                .createdAt(menu.getCreatedAt())
                .build();
    }
}
