package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private String name;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer minOrderPrice;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .build();
    }
}
