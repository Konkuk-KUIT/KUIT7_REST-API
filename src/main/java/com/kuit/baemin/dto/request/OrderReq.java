package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReq {

    @NotNull(message = "식당 ID는 필수입니다")
    private Long restaurantId;

    @NotNull(message = "배송 주소 ID는 필수입니다")
    private Long deliveryAddressId;

    private String requestMemo;

    @NotNull(message = "주문 항목은 필수입니다")
    private List<OrderItemReq> orderItems;
}
