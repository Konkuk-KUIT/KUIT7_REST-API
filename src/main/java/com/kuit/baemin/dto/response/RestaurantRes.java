package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantRes {

    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String roadAddress;
    private String detailAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal minOrderAmount;
    private BigDecimal deliveryFee;
    private String imageUrl;
    private RestaurantStatus status;
    private LocalDateTime createdAt;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .roadAddress(restaurant.getRoadAddress())
                .detailAddress(restaurant.getDetailAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .minOrderAmount(restaurant.getMinOrderAmount())
                .deliveryFee(restaurant.getDeliveryFee())
                .imageUrl(restaurant.getImageUrl())
                .status(restaurant.getStatus())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }
}
