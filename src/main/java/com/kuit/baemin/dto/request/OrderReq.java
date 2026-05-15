package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderReq {

    @NotNull private Long userId;
    @NotNull private Long storeId;

    @NotEmpty(message = "최소 1개 이상의 메뉴를 주문해야 합니다.")
    private List<OrderMenuReq> orderMenus;

}
