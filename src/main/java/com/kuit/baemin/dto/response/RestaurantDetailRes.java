package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantDetailRes {
    private Long id;
    private String name;
    private String address;

    public static RestaurantDetailRes from(Restaurant restaurant) {
        return RestaurantDetailRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();
    }
}