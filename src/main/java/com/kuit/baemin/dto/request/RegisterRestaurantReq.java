package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class RegisterRestaurantReq {

    @NotBlank(message = "가게 이름은 필수입니다.")
    private String storeName;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    @PositiveOrZero(message = "최소 주문 금액은 0원 이상입니다.")
    private Long minimum_delivery_price;
}
