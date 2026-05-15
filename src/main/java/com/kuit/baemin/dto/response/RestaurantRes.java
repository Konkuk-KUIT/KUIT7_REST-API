package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRes {

    private Long id;
    private String name;
    private String phoneNumber;
    private String roadAddress;
    private String detailAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long minOrderAmount;
    private Long deliveryFee;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RestaurantRes from(Restaurant restaurant) {
        return RestaurantRes.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .phoneNumber(restaurant.getPhoneNumber())
                .roadAddress(restaurant.getRoadAddress())
                .detailAddress(restaurant.getDetailAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .minOrderAmount(restaurant.getMinOrderAmount())
                .deliveryFee(restaurant.getDeliveryFee())
                .status(restaurant.getStatus().toString())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .build();
    }
}
