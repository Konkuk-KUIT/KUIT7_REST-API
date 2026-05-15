package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RestaurantReq {

    @NotNull(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;

    @NotBlank(message = "식당 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    @NotNull(message = "위도는 필수입니다.")
    private BigDecimal lat;

    @NotNull(message = "경도는 필수입니다.")
    private BigDecimal lng;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    private Integer minOrderPrice;

    @NotNull(message = "배달비는 필수입니다.")
    private Integer deliveryFee;
}