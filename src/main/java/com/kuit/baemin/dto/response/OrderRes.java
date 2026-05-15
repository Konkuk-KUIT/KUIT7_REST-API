package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderRes {
    private Long id;
    private Long memberId;
    private Long restaurantId;
    private String status;
    private Integer totalPrice;
    private String paymentMethod;
    private LocalDateTime orderedAt;

    public static OrderRes from(Order order) {
        return OrderRes.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .restaurantId(order.getRestaurant().getId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .paymentMethod(order.getPaymentMethod())
                .orderedAt(order.getOrderedAt())
                .build();
    }
}