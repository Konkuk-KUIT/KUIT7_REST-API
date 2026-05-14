package com.kuit.baemin.dto.request;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;;

@Getter
public class OrderMenuReq {

    @NotNull
    private Long menuId;

    @NotNull
    private Integer quantity;
}
