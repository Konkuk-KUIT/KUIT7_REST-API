package com.kuit.baemin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuit.baemin.domain.order.OrderEntity;
import com.kuit.baemin.domain.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderRes {
    private Long userId;
    private Long storeId;
    private Long addressId;
    @JsonProperty("request_message")
    private String requestMessage;
    private OrderStatus orderStatus;
    private Integer deliveryFee;
    private Integer totalPrice;

    public static CreateOrderRes from(OrderEntity order) {
        return CreateOrderRes.builder()
                .userId(order.getMember().getId())
                .storeId(order.getRestaurant().getId())
                .addressId(order.getAddress().getId())
                .requestMessage(order.getRequestMessage())
                .orderStatus(order.getOrderStatus())
                .deliveryFee(order.getDeliveryFee())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
