package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderRes {

    private Long id;
    private String restaurantName;
    private OrderStatus orderStatus;
    private Integer totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemRes> items;

    public static OrderRes of(Orders order, Integer totalPrice, List<OrderItemRes> items) {
        return OrderRes.builder()
                .id(order.getId())
                .restaurantName(order.getRestaurant().getName())
                .orderStatus(order.getOrderStatus())
                .totalPrice(totalPrice)
                .createdAt(order.getCreatedAt())
                .items(items)
                .build();
    }
}
