package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateRestaurantReq {

    @NotBlank(message = "음식점 이름은 필수입니다.")
    private String restaurantName;

    @NotBlank(message = "카테고리는 필수입니다.")
    private String category;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    private Float minOrderAmount;

    @NotNull(message = "배달비는 필수입니다.")
    private Float deliveryFee;
}