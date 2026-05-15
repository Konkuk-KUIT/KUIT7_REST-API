package com.kuit.baemin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateReq {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long restaurantId;

    @NotBlank(message = "배달 주소는 필수입니다.")
    @Size(max = 200, message = "배달 주소는 200자 이하여야 합니다.")
    private String deliveryRoadAddress;

    @Size(max = 200, message = "상세 주소는 200자 이하여야 합니다.")
    private String deliveryDetailAddress;

    @NotEmpty(message = "주문 항목은 1개 이상이어야 합니다.")
    @Valid
    private List<OrderItemReq> items;
}
