package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.List;

@Getter
public class OrderReq {
    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long restaurantId;

    @Size(min = 1, message = "최소 한 개 이상의 메뉴를 주문해야 합니다.")
    private List<OrderItemReq> orderItems;

    @NotNull(message = "총 가격은 필수입니다.")
    private Long totalPrice;
}