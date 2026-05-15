package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class OrderMenuReq {

    @NotNull
    @Positive
    private Long menuId;

    @NotNull
    @Positive
    private Integer quantity;
}
