package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderReq {

    @NotNull(message = "식당 ID는 필수입니다.")
    private Long restaurantId;

    @NotNull(message = "주소 ID는 필수입니다.")
    private Long addressId;

    @NotNull(message = "주문 항목은 필수입니다.")
    private List<OrderItemReq> orderItems;
}