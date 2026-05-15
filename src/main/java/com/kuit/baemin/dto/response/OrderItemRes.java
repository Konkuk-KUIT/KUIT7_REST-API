package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRes {

    private Long id;
    private Long orderId;
    private Long menuId;
    private String menuNameSnapshot;
    private Long menuPriceSnapshot;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderItemRes from(OrderItem orderItem) {
        return OrderItemRes.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .menuId(orderItem.getMenu().getId())
                .menuNameSnapshot(orderItem.getMenuNameSnapshot())
                .menuPriceSnapshot(orderItem.getMenuPriceSnapshot())
                .quantity(orderItem.getQuantity())
                .status(orderItem.getStatus())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .build();
    }
}
