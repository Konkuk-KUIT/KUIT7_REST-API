package com.kuit.baemin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateReq {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "음식점 ID는 필수입니다.")
    private Long restaurantId;

    @NotNull(message = "주소 ID는 필수입니다.")
    private Long addressId;

    @Valid
    @NotEmpty(message = "주문 메뉴는 최소 1개 이상이어야 합니다.")
    private List<OrderItemCreateReq> items;
}
