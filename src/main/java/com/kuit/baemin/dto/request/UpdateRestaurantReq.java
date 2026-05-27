package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class UpdateRestaurantReq {

    private String storeName;

    @PositiveOrZero(message = "최소 주문 금액은 0원 이상입니다.")
    private Long minimum_delivery_price;
}
