package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRes {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private Long deliveryAddressId;
    private String orderStatus;
    private String requestMemo;
    @Builder.Default
    private List<OrderItemRes> orderItems = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderRes from(Order order) {
        return OrderRes.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .restaurantId(order.getRestaurant().getId())
                .deliveryAddressId(order.getDeliveryAddressId())
                .orderStatus(order.getOrderStatus().toString())
                .requestMemo(order.getRequestMemo())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
