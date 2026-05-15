package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private String categoryName;
    private String name;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
    private Integer minOrderPrice;
    private Integer deliveryFee;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .categoryName(restaurant.getCategory().getName())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .lat(restaurant.getLat())
                .lng(restaurant.getLng())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .deliveryFee(restaurant.getDeliveryFee())
                .status(restaurant.getStatus().name())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .build();
    }
}