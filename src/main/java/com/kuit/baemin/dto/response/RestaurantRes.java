package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private String name;
    private String address;
    private String addressDetail;
    private String phoneNumber;
    private BigDecimal minOrderPrice;
    private BigDecimal deliveryFee;

    public static RestaurantRes from(Restaurant restaurant) {

        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .addressDetail(restaurant.getAddressDetail())
                .phoneNumber(restaurant.getPhoneNumber())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .deliveryFee(restaurant.getDeliveryFee())
                .build();
    }
}
