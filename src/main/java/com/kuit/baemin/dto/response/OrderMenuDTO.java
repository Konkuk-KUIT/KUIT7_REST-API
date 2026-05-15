package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Order.OrderMenu;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderMenuDTO {

    private String menuName;
    private Integer quantity;
    private BigDecimal price;

    public static OrderMenuDTO from(OrderMenu orderMenu) {
        return new OrderMenuDTO(
                orderMenu.getMenu().getMenuName(),
                orderMenu.getQuantity(),
                orderMenu.getOrderMenuPrice()
        );
    }
}
