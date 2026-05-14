package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 음식점 조회 응답 DTO
 */
@Getter
@Builder
public class RestaurantRes {
    private Long id;

    private String name;

    private String phoneNumber;

    private String roadAddress;

    private String detailAddress;

    private BigDecimal minOrderPrice;

    private BigDecimal deliveryFee;

    private String imageUrl;

    private RestaurantStatus status;

    public static RestaurantRes from(Restaurant restaurant) {

        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .phoneNumber(restaurant.getPhoneNumber())
                .roadAddress(restaurant.getRoadAddress())
                .detailAddress(restaurant.getDetailAddress())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .deliveryFee(restaurant.getDeliveryFee())
                .imageUrl(restaurant.getImageUrl())
                .status(restaurant.getStatus())
                .build();
    }
}
