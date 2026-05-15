package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDetailRes { // 반드시 public을 붙여주세요!
    private Long orderId;
    private String restaurantName;
    private Long totalPrice;
    private String status;
}