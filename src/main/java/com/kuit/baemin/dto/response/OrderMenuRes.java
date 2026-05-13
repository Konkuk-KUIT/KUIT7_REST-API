package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.OrderMenu.OrderMenu;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderMenuRes {
    private String menuName;
    private BigDecimal price;
    private int quantity;

    public static OrderMenuRes from(OrderMenu orderMenu) {

        return OrderMenuRes.builder()
                .menuName(orderMenu.getMenu().getName())
                .price(orderMenu.getPrice())
                .quantity(orderMenu.getQuantity())
                .build();
    }
}