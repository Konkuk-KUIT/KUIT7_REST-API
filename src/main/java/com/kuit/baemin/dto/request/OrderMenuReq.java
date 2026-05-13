package com.kuit.baemin.dto.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class OrderMenuReq {

    @NotNull
    private Long menuId;

    @NotNull
    private Integer quantity;
}
