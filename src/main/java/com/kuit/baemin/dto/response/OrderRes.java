package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderRes {

    private Long id;
    private Long memberId;
    private Long restaurantId;
    private String restaurantName;
    private Long addressId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderRes from(Order order) {
        return OrderRes.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .restaurantId(order.getRestaurant().getId())
                .restaurantName(order.getRestaurant().getName())
                .addressId(order.getAddress().getId())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}