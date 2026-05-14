package com.kuit.baemin.dto.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class OrderReq {

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long addressId;

    @NotNull
    private List<OrderMenuReq> menus;
}
