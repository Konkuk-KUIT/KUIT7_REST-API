package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderReq {

    @NotNull
    private Long memberId;

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long addressId;

    private String paymentMethod;

    private String requestNote;

    @NotEmpty(message = "주문 항목은 최소 1개 이상이어야 합니다.")
    private List<OrderItemReq> items;

    @Getter
    public static class OrderItemReq {
        @NotNull
        private Long menuId;

        @NotNull
        private Integer quantity;
    }
}