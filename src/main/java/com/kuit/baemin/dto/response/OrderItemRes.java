package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderItemRes {

    private Long id;
    private Long menuId;
    private String menuName;
    private Integer price;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;

    public static OrderItemRes from(OrderItem orderItem) {
        return OrderItemRes.builder()
                .id(orderItem.getId())
                .menuId(orderItem.getMenu().getId())
                .menuName(orderItem.getMenu().getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .status(orderItem.getStatus().name())
                .createdAt(orderItem.getCreatedAt())
                .build();
    }
}