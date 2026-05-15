package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String address;
    private Integer minOrderPrice;
    private Integer deliveryFee;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .categoryId(restaurant.getCategory().getId())
                .categoryName(restaurant.getCategory().getName())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .deliveryFee(restaurant.getDeliveryFee())
                .build();
    }
}
