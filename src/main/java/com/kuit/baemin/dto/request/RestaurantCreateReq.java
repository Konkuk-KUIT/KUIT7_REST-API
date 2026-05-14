package com.kuit.baemin.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RestaurantCreateReq {
    private String name;

    private String phoneNumber;

    private String roadAddress;

    private String detailAddress;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private BigDecimal minOrderPrice;

    private BigDecimal deliveryFee;

    private String imageUrl;

    private Long categoryId;
}
