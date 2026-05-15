package com.kuit.baemin.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderMenuReq {
    private Long menuId;
    private int quantity;
}
