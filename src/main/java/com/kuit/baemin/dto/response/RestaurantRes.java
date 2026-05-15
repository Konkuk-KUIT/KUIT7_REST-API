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
    private Long minimum_delivery_price;
    private Long order_count;
    private RestaurantStatus restaurant_status;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .minimum_delivery_price(restaurant.getMinimum_delivery_price())
                .order_count(restaurant.getOrder_count())
                .restaurant_status(restaurant.getStatus())
                .build();
    }
}
