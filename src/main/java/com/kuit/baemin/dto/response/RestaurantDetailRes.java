package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RestaurantDetailRes {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String phone;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer minOrderPrice;
    private Integer deliveryFee;
    private RestaurantStatus status;

    public static RestaurantDetailRes from(Restaurant restaurant) {
        return RestaurantDetailRes.builder()
                .id(restaurant.getId())
                .categoryId(restaurant.getCategory().getId())
                .categoryName(restaurant.getCategory().getName())
                .name(restaurant.getName())
                .phone(restaurant.getPhone())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .deliveryFee(restaurant.getDeliveryFee())
                .status(restaurant.getStatus())
                .build();
    }
}
