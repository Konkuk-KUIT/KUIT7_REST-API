package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantRes {
    private Long id;
    private String restaurantName;
    private String category;
    private String address;
    private Float minOrderAmount;
    private Float deliveryFee;
    private Float rate;
    private Boolean isOpen;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .category(restaurant.getCategory())
                .address(restaurant.getAddress())
                .minOrderAmount(restaurant.getMinOrderAmount())
                .deliveryFee(restaurant.getDeliveryFee())
                .rate(restaurant.getRate())
                .isOpen(restaurant.getIsOpen())
                .build();
    }
}