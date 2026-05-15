package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderItemReq {
    @NotNull(message = "메뉴 ID는 필수입니다.")
    private Long menuId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private Integer quantity;

    @NotNull(message = "가격은 필수입니다.")
    private Long price;
}