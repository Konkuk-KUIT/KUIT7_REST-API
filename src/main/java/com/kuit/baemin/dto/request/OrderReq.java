package com.kuit.baemin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Getter
public class OrderReq {

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long addressId;

    @NotEmpty
    @Valid
    private List<OrderMenuReq> menus;
}