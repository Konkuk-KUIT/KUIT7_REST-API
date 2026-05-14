package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private String name;
    private int minimumOrderPrice;
    private String address;
    private RestaurantStatus status;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .minimumOrderPrice(restaurant.getMinOrderPrice())
                .address(restaurant.getAddress())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }
}