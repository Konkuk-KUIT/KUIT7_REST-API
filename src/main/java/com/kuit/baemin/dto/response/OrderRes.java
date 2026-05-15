package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order.OrderProgressStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderRes {

    private Long orderId;
    private Long restaurantId;
    private String restaurantName;
    private Integer totalPrice;
    private OrderProgressStatus orderStatus;
    private LocalDateTime orderedAt;
    private List<OrderItemRes> items;

    public static OrderRes from(Order order) {
        return OrderRes.builder()
                .orderId(order.getId())
                .restaurantId(order.getRestaurant().getId())
                .restaurantName(order.getRestaurant().getName())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .orderedAt(order.getCreatedAt())
                .items(order.getOrderItems().stream().map(OrderItemRes::from).toList())
                .build();
    }
}
