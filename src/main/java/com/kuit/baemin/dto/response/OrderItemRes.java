package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemRes {

    private Long menuId;
    private String menuName;
    private Integer quantity;
    private Integer priceAtOrder;
    private Integer subtotal;

    public static OrderItemRes from(OrderItem item) {
        return OrderItemRes.builder()
                .menuId(item.getMenu().getId())
                .menuName(item.getMenu().getName())
                .quantity(item.getQuantity())
                .priceAtOrder(item.getPriceAtOrder())
                .subtotal(item.subtotal())
                .build();
    }
}
