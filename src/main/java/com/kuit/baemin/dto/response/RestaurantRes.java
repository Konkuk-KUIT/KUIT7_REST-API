package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RestaurantRes {

    private Long storeId;
    private String name;
    private String category;
    private String phone;
    private Integer minOrderPrice;
    private Integer deliveryFee;
    private BigDecimal rating;
    private Boolean isOpen;
    private RestaurantStatus status;

    public static RestaurantRes from(Restaurant store){
        return RestaurantRes.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category(store.getCategory())
                .phone(store.getPhone())
                .minOrderPrice(store.getMinOrderPrice())
                .deliveryFee(store.getDeliveryFee())
                .rating(store.getRating())
                .isOpen(store.getIsOpen())
                .status(store.getStatus())
                .build();
    }
}
